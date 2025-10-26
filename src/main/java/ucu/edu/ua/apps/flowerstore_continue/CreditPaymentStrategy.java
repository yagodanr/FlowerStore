package flowerstore_continue;

public class CreditPaymentStrategy implements PaymentStrategy {

    @Override
    public String getName() {
        return "Credit username";
    }

    @Override
    public int pay(double amount) {
        // TODO Auto-generated method stub
        System.out.println("Sending request to Credit payment");
        return 0;
    }

}
