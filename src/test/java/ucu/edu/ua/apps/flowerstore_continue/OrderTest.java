package ucu.edu.ua.apps.flowerstore_continue;

import flowers.Flower;
import flowers.FlowerColor;
import flowers.FlowerType;
import flowerstore_continue.CreditPaymentStrategy;
import flowerstore_continue.DHLDeliveryStrategy;
import flowerstore_continue.DeliveryStrategy;
import flowerstore_continue.Item;
import flowerstore_continue.Order;
import flowerstore_continue.PayPalPaymentStrategy;
import flowerstore_continue.PaymentStrategy;
import flowerstore_continue.PostDeliveryStrategy;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//CHECKSTYLE:OFF
public class OrderTest {

    private Flower rose;
    private Flower tulip;

    @BeforeEach
    void setup() {
        rose = new Flower(10.0, FlowerColor.RED, 50.0, FlowerType.ROSE);
        tulip = new Flower(15.0, FlowerColor.YELLOW, 30.0, FlowerType.TULIP);
    }

    @Test
    void testAddItemAndTotalPrice() {
        PaymentStrategy payment = new PayPalPaymentStrategy();
        DeliveryStrategy delivery = new DHLDeliveryStrategy();

        Order order = new Order("user123", payment, delivery);
        order.addItem(new Item(rose, 2));
        order.addItem(new Item(tulip, 3));

        double expectedPrice = 2 * 50.0 + 3 * 30.0;
        Assertions.assertEquals(expectedPrice, order.getTotalPrice(), 0.001);
    }

    @Test
    void testPayWithPayPalStrategy() {
        PaymentStrategy payment = new PayPalPaymentStrategy();
        DeliveryStrategy delivery = new DHLDeliveryStrategy();
        Order order = new Order("user1", payment, delivery);

        order.addItem(new Item(rose, 1));

        int result = order.pay(order.getTotalPrice());
        Assertions.assertEquals(0, result, "PayPal payment should succeed (return 0)");
    }

    @Test
    void testPayWithCreditCardStrategy() {
        PaymentStrategy payment = new CreditPaymentStrategy();
        DeliveryStrategy delivery = new DHLDeliveryStrategy();
        Order order = new Order("user2", payment, delivery);

        order.addItem(new Item(tulip, 4));

        int result = order.pay(order.getTotalPrice());
        Assertions.assertEquals(0, result, "Credit card payment should succeed (return 0)");
    }

    @Test
    void testDHLDelivery() {
        PaymentStrategy payment = new PayPalPaymentStrategy();
        DeliveryStrategy delivery = new DHLDeliveryStrategy();
        Order order = new Order("user3", payment, delivery);

        order.addItem(new Item(rose, 1));

        String status = order.orderDelivery("Kyiv");
        Assertions.assertTrue(status.toLowerCase().contains(" 15 minutes"));
    }

    @Test
    void testPostDelivery() {
        PaymentStrategy payment = new PayPalPaymentStrategy();
        DeliveryStrategy delivery = new PostDeliveryStrategy();
        Order order = new Order("user4", payment, delivery);

        order.addItem(new Item(rose, 1));

        String status = order.orderDelivery("Lviv");
        Assertions.assertTrue(status.toLowerCase().contains(" 5 minutes"));
    }
}
//CHECKSTYLE:ON