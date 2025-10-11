
import org.junit.jupiter.api.Test;

import ucu.edu.ua.apps.Flower;
import ucu.edu.ua.apps.FlowerColor;
import ucu.edu.ua.apps.FlowerPack;

import static org.junit.jupiter.api.Assertions.*;

public class FlowerPackTest {

    private static final double DELTA = 1e-6;
    private static final double ROSE_PRICE = 10.0;
    private static final double TULIP_PRICE = 5.0;
    private static final double CHAMOMILE_PRICE = 3.0;
    private static final int FLOWER_COUNT = 5;
    private static final int SMALL_COUNT = 2;

    private Flower createFlower(final FlowerColor color, final double price, final double sepalLength) {
        final Flower flower = new Flower();
        flower.setColor(color);
        flower.setPrice(price);
        flower.setSepalLength(sepalLength);
        return flower;
    }

    @Test
    void getPriceReturnsCorrectValue() {
        final Flower rose = createFlower(FlowerColor.RED, ROSE_PRICE, 10.0);
        final FlowerPack pack = new FlowerPack(rose, FLOWER_COUNT);

        final double expectedPrice = ROSE_PRICE * FLOWER_COUNT;
        assertEquals(expectedPrice, pack.getPrice(), DELTA,
                "getPrice() should return flower price multiplied by count");
    }

    @Test
    void containsReturnsTrueForEqualFlower() {
        final Flower tulip = createFlower(FlowerColor.YELLOW, TULIP_PRICE, 8.0);
        final FlowerPack pack = new FlowerPack(tulip, SMALL_COUNT);

        final Flower sameTulip = createFlower(FlowerColor.YELLOW, TULIP_PRICE, 8.0);
        assertTrue(pack.contains(sameTulip),
                "contains() should return true for an equal flower");
    }

    @Test
    void containsReturnsFalseForDifferentFlower() {
        final Flower chamomile = createFlower(FlowerColor.RED, CHAMOMILE_PRICE, 6.0);
        final FlowerPack pack = new FlowerPack(chamomile, SMALL_COUNT);

        final Flower tulip = createFlower(FlowerColor.RED, TULIP_PRICE, 8.0);
        assertFalse(pack.contains(tulip),
                "contains() should return false for a different flower");
    }

    @Test
    void changingOriginalFlowerDoesNotAffectPack() {
        final Flower rose = createFlower(FlowerColor.RED, ROSE_PRICE, 7.0);
        final FlowerPack pack = new FlowerPack(rose, SMALL_COUNT);

        rose.setPrice(ROSE_PRICE * 10); // mutate original

        final double expectedPrice = ROSE_PRICE * SMALL_COUNT;
        assertEquals(expectedPrice, pack.getPrice(), DELTA,
                "FlowerPack should copy flower defensively to prevent external mutation");
    }
}
