import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import flowers.Flower;
import flowers.FlowerBucket;
import flowers.FlowerColor;
import flowers.FlowerType;
import flowers.Store;

import org.junit.jupiter.api.Assertions;

import java.util.List;

//CHECKSTYLE:OFF
public class StoreTest {
    private static final int PRICE_THRESHOLD = 50;
    private static final int ROSE_PRICE = 10;
    private static final int FIRST_BUCKET_SIZE = 5;
    private static final int SECOND_BUCKET_SIZE = 3;

    private Store store;
    private FlowerBucket firstBucket;
    private FlowerBucket secondBucket;
    private Flower ROSE;

    @BeforeEach
    void setUp() {
        store = new Store();
        ROSE = new Flower(0, FlowerColor.RED, ROSE_PRICE, FlowerType.ROSE);

        firstBucket = new FlowerBucket();
        firstBucket.add(ROSE, FIRST_BUCKET_SIZE);

        secondBucket = new FlowerBucket();
        secondBucket.add(ROSE, SECOND_BUCKET_SIZE);
    }

    @Test
    void testSearchByPrice() {
        List<FlowerBucket> result = store.search(PRICE_THRESHOLD);
        Assertions.assertTrue(result.isEmpty());

        store.add(firstBucket);  // Assuming total price is 50
        result = store.search(PRICE_THRESHOLD);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(firstBucket, result.get(0));

        store.add(secondBucket);
        result = store.search(PRICE_THRESHOLD);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(firstBucket, result.get(0));
        Assertions.assertEquals(secondBucket, result.get(1));
    }

    @Test
    void testSearchByFlower() {
        List<FlowerBucket> result = store.search(ROSE);
        Assertions.assertTrue(result.isEmpty());

        store.add(firstBucket);
        store.add(secondBucket);

        result = store.search(ROSE);
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(firstBucket));
        Assertions.assertTrue(result.contains(secondBucket));
    }
}
//CHECKSTYLE:ON
