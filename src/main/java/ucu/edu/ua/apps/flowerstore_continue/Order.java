package flowerstore_continue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Order {
    private String orderId;
    private String userId;
    private List<Item> items = new ArrayList<>();
    @Setter
    private PaymentStrategy paymentStrategy;
    @Setter
    private DeliveryStrategy deliveryStrategy;

    public Order(String userId,
                 PaymentStrategy paymentStrategy, DeliveryStrategy deliveryStrategy
                 ) {
        this.userId = userId;
        this.paymentStrategy = paymentStrategy;
        this.deliveryStrategy = deliveryStrategy;
        orderId = UUID.randomUUID().toString();
    }

    public void addItem(Item item) {
        items.add(item);
    }
    public void removeItem(Item item) {
        items.remove(item);
    }

    public double getTotalPrice() {
        return items.stream().mapToDouble(x -> x.getPrice()).sum();
    }

    public int pay(double amount) {
        return paymentStrategy.pay(amount);
    }

    public String orderDelivery(String destination) {
        deliveryStrategy.contactDeliveryService(orderId, userId, destination);
        return deliveryStrategy.getStatus(orderId);
    }
}
