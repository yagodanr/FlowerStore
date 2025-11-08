package ucu.edu.ua.apps.flowerstore_continue;

import org.junit.jupiter.api.Test;

import flowerstore_continue.DeliveryStrategy;
import flowerstore_continue.Item;
import flowerstore_continue.Order;
import flowerstore_continue.PaymentStrategy;

import org.junit.jupiter.api.Assertions;

class OrderTest {

    static class TestPayment implements PaymentStrategy {
        double lastAmount = -1;

        @Override
        public int pay(double amount) {
            lastAmount = amount;
            return 0;
        }

        @Override
        public String getName() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getName'");
        }
    }

    static class TestDelivery implements DeliveryStrategy {
        String lastOrderId;
        String lastUserId;
        String lastDestination;

        @Override
        public int contactDeliveryService(String orderId, String userId, String destination) {
            lastOrderId = orderId;
            lastUserId = userId;
            lastDestination = destination;
            return 0;
        }

        @Override
        public String getStatus(String orderId) {
            return "ok:" + orderId;
        }
    }

    static class TestItem extends Item {
        private final double price;

        TestItem(double price) {
            this.price = price;
        }

        @Override
        public double getPrice() {
            return price;
        }

        @Override
        public String getDescription() {
            return "test";
        }
    }

    @Test
    void orderStoresUserId() {
        Order o = new Order("u1", new TestPayment(), new TestDelivery());
        Assertions.assertEquals("u1", o.getUserId());
        Assertions.assertNotNull(o.getOrderId());
    }

    @Test
    void addItemsWorks() {
        Order o = new Order("u1", new TestPayment(), new TestDelivery());

        o.addItem(new TestItem(10));
        o.addItem(new TestItem(5));

        Assertions.assertEquals(2, o.getItems().size());
        Assertions.assertEquals(15.0, o.getTotalPrice());
    }

    @Test
    void removeItemWorks() {
        Order o = new Order("u1", new TestPayment(), new TestDelivery());
        TestItem a = new TestItem(10);
        TestItem b = new TestItem(5);

        o.addItem(a);
        o.addItem(b);
        o.removeItem(a);

        Assertions.assertEquals(1, o.getItems().size());
        Assertions.assertEquals(5.0, o.getTotalPrice());
    }

    @Test
    void paymentDelegatesToStrategy() {
        TestPayment p = new TestPayment();
        Order o = new Order("u1", p, new TestDelivery());

        o.addItem(new TestItem(12));
        o.addItem(new TestItem(8));

        int r = o.pay(o.getTotalPrice());

        Assertions.assertEquals(20.0, p.lastAmount);
        Assertions.assertEquals(0, r);
    }

    @Test
    void deliveryDelegatesToStrategy() {
        TestPayment p = new TestPayment();
        TestDelivery d = new TestDelivery();
        Order o = new Order("u1", p, d);

        String result = o.orderDelivery("Kyiv");

        Assertions.assertEquals(o.getOrderId(), d.lastOrderId);
        Assertions.assertEquals("u1", d.lastUserId);
        Assertions.assertEquals("Kyiv", d.lastDestination);
        Assertions.assertEquals("ok:" + o.getOrderId(), result);
    }
}
