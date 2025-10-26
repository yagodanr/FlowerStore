package flowerstore_continue;

public class PostDeliveryStrategy implements DeliveryStrategy{

    @Override
    public int contactDeliveryService(String orderId, String userId, String destination) {
        System.out.println("Contacting Post");
        return 0;
    }

    @Override
    public String getStatus(String orderId) {
        return "Will be back in 5 minutes";
    }

}
