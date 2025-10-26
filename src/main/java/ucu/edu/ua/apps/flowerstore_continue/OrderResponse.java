package flowerstore_continue;

public record OrderResponse(
        String orderId,
        String userId,
        String payment,
        String delivery,
        double totalPrice
) { }