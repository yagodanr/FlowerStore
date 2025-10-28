package flowerstore_continue;

public interface DeliveryStrategy {
    int contactDeliveryService(
        String orderId, String userId, String destination
    );
    String getStatus(String orderId);
}
