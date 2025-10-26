package flowerstore_continue;

import flowers.Flower;
import flowers.FlowerColor;
import flowers.FlowerType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    // Simple in-memory "database"
    private final Map<String, Order> orders = new java.util.concurrent.ConcurrentHashMap<>();

    // Endpoint to create a new order
    @PostMapping("/create")
    public OrderResponse createOrder(@RequestParam String userId,
                             @RequestParam String payment,
                             @RequestParam String delivery) {

        PaymentStrategy paymentStrategy = switch (payment.toLowerCase()) {
            case "paypal" -> new PayPalPaymentStrategy();
            case "credit" -> new CreditPaymentStrategy();
            default -> throw new IllegalArgumentException("Unknown payment strategy: " + payment);
        };

        DeliveryStrategy deliveryStrategy = switch (delivery.toLowerCase()) {
            case "dhl" -> new DHLDeliveryStrategy();
            case "post" -> new PostDeliveryStrategy();
            default -> throw new IllegalArgumentException("Unknown delivery strategy: " + delivery);
        };

        Order order = new Order(userId, paymentStrategy, deliveryStrategy);
        orders.put(order.getOrderId(), order);
        return new OrderResponse(order.getOrderId(), userId, payment, delivery, 0);
    }

    // Add flower items to the order
    @PostMapping("/{orderId}/add")
    public List<Item> addItem(@PathVariable String orderId,
                         @RequestParam FlowerType type,
                         @RequestParam FlowerColor color,
                         @RequestParam double price,
                         @RequestParam int count) {

        Order order = getOrder(orderId);
        Flower flower = new Flower(15.0, color, price, type);
        order.addItem(new Item(flower, count));
        return order.getItems();
    }

    // Get total price
    @GetMapping("/{orderId}/price")
    public double getTotalPrice(@PathVariable String orderId) {
        return getOrder(orderId).getTotalPrice();
    }

    // Pay for the order
    @PostMapping("/{orderId}/pay")
    public String pay(@PathVariable String orderId) {
        Order order = getOrder(orderId);
        int result = order.pay(order.getTotalPrice());
        return result == 0 ? "Payment successful" : "Payment failed";
    }

    // Deliver the order
    @PostMapping("/{orderId}/deliver")
    public String deliver(@PathVariable String orderId,
                          @RequestParam String destination) {
        Order order = getOrder(orderId);
        return order.orderDelivery(destination);
    }

    private Order getOrder(String id) {
        Order order = orders.get(id);
        if (order == null) {
            throw new IllegalArgumentException("Order not found: " + id);
        }
        return order;
    }
}
