package flowerstore_continue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private String orderId;
    private String userId;
    private List<Item> items = new ArrayList<>();

    Order(String userId) {
        this.userId = userId;
        orderId = UUID.randomUUID().toString();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public double getTotalPrice() {
        return items.stream().mapToDouble(x -> x.getPrice()).sum();
    }

}
