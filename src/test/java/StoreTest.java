

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ucu.edu.ua.apps.Flower;
import ucu.edu.ua.apps.FlowerBucket;
import ucu.edu.ua.apps.FlowerColor;
import ucu.edu.ua.apps.FlowerType;
import ucu.edu.ua.apps.Store;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class StoreTest {
    private Store store;
    private FlowerBucket bucket1;
    private FlowerBucket bucket2;
    private Flower rose;

    @BeforeEach
    void setUp() {
        store = new Store();
        rose = new Flower(0, FlowerColor.RED, 10, FlowerType.ROSE);

        bucket1 = new FlowerBucket();
        bucket1.add(rose, 5);  // bucket with 5 roses

        bucket2 = new FlowerBucket();
        bucket2.add(rose, 3);  // bucket with 3 roses
    }

    @Test
    void testSearchByPrice() {
        List<FlowerBucket> result = store.search(50);
        assertTrue(result.isEmpty());

        store.add(bucket1);  // Assuming total price is 50
        result = store.search(50);
        assertEquals(1, result.size());
        assertEquals(bucket1, result.get(0));

        store.add(bucket2);
        result = store.search(50);
        assertEquals(2, result.size());
        assertEquals(bucket1, result.get(0));
        assertEquals(bucket2, result.get(1));
    }

    @Test
    void testSearchByFlower() {
        List<FlowerBucket> result = store.search(rose);
        assertTrue(result.isEmpty());

        store.add(bucket1);
        store.add(bucket2);

        result = store.search(rose);
        assertEquals(2, result.size());
        assertTrue(result.contains(bucket1));
        assertTrue(result.contains(bucket2));
    }
}
