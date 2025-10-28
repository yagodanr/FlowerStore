package flowerstore_continue;


import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    // Simple in-memory "database"
    private final Map<String, Order> orders = new java.util.concurrent.ConcurrentHashMap<>();

    // Get total price
    @GetMapping("/{orderId}/price")
    public double getTotalPrice(@PathVariable String orderId) {
        return getOrder(orderId).getTotalPrice();
    }


    private Order getOrder(String id) {
        Order order = orders.get(id);
        if (order == null) {
            throw new IllegalArgumentException("Order not found: " + id);
        }
        return order;
    }
}
