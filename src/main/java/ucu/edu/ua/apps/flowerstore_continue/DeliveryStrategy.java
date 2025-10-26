package flowerstore_continue;

public interface DeliveryStrategy {
    public int contactDeliveryService(String orderId, String userId, String destination);
    public String getStatus(String orderId);
}
