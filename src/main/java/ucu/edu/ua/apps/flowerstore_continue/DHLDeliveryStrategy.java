package flowerstore_continue;

public class DHLDeliveryStrategy implements DeliveryStrategy {
    @Override
    public int contactDeliveryService(String orderId, String userId, String destination) {
        System.out.println("Contacting DHL");
        return 0;
    }

    @Override
    public String getStatus(String orderId) {
        return "Will be back in 15 minutes";
    }
}
