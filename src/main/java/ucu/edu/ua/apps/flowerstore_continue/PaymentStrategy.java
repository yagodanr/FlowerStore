package flowerstore_continue;

public interface PaymentStrategy {
    public String getName();
    // returns status
    public int pay(double amount);
}
