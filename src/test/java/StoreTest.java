import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ucu.edu.ua.apps.Flower;
import ucu.edu.ua.apps.FlowerBucket;
import ucu.edu.ua.apps.FlowerColor;
import ucu.edu.ua.apps.FlowerType;
import ucu.edu.ua.apps.Store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertTrue(result.isEmpty());

        store.add(firstBucket);  // Assuming total price is 50
        result = store.search(PRICE_THRESHOLD);
        assertEquals(1, result.size());
        assertEquals(firstBucket, result.get(0));

        store.add(secondBucket);
        result = store.search(PRICE_THRESHOLD);
        assertEquals(2, result.size());
        assertEquals(firstBucket, result.get(0));
        assertEquals(secondBucket, result.get(1));
    }

    @Test
    void testSearchByFlower() {
        List<FlowerBucket> result = store.search(ROSE);
        assertTrue(result.isEmpty());

        store.add(firstBucket);
        store.add(secondBucket);

        result = store.search(ROSE);
        assertEquals(2, result.size());
        assertTrue(result.contains(firstBucket));
        assertTrue(result.contains(secondBucket));
    }
}
//CHECKSTYLE:ON
