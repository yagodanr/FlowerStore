package flowerstore_continue;

public interface PaymentStrategy {
    String getName();
    // returns status
    int pay(double amount);
}
