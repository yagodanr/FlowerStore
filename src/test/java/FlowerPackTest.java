import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import ucu.edu.ua.apps.Flower;
import ucu.edu.ua.apps.FlowerColor;
import ucu.edu.ua.apps.FlowerPack;

//CHECKSTYLE:OFF
public class FlowerPackTest {
    private static final double DELTA = 1e-6;
    private static final double ROSE_PRICE = 10.0;
    private static final double TULIP_PRICE = 5.0;
    private static final double CHAMOMILE_PRICE = 3.0;
    private static final int FLOWER_COUNT = 5;
    private static final int SMALL_COUNT = 2;

    private Flower createFlower(final FlowerColor flowerColor,
            final double flowerPrice, final double sepalLength) {
        final Flower FLOWER = new Flower();
        FLOWER.setColor(flowerColor);
        FLOWER.setPrice(flowerPrice);
        FLOWER.setSepalLength(sepalLength);
        return FLOWER;
    }

    @Test
    void getPriceReturnsCorrectValue() {
        final Flower ROSE = createFlower(FlowerColor.RED, ROSE_PRICE, 10.0);
        final FlowerPack PACK = new FlowerPack(ROSE, FLOWER_COUNT);
        final double EXPECTED_PRICE = ROSE_PRICE * FLOWER_COUNT;

        Assertions.assertEquals(EXPECTED_PRICE, PACK.getPrice(), DELTA);
    }

    @Test
    void containsReturnsTrueForEqualFlower() {
        final Flower TULIP = createFlower(FlowerColor.YELLOW, TULIP_PRICE, 8.0);
        final FlowerPack PACK = new FlowerPack(TULIP, SMALL_COUNT);
        final Flower SAME_TULIP = createFlower(
            FlowerColor.YELLOW, TULIP_PRICE, 8.0
        );

        Assertions.assertTrue(PACK.contains(SAME_TULIP));
    }

    @Test
    void containsReturnsFalseForDifferentFlower() {
        final Flower CHAMOMILE = createFlower(
            FlowerColor.RED, CHAMOMILE_PRICE, 6.0
        );
        final FlowerPack PACK = new FlowerPack(CHAMOMILE, SMALL_COUNT);
        final Flower TULIP = createFlower(FlowerColor.RED, TULIP_PRICE, 8.0);

        Assertions.assertFalse(PACK.contains(TULIP));
    }

    @Test
    void changingOriginalFlowerDoesNotAffectPack() {
        final Flower ROSE = createFlower(FlowerColor.RED, ROSE_PRICE, 7.0);
        final FlowerPack PACK = new FlowerPack(ROSE, SMALL_COUNT);
        final double PRICE_MULTIPLIER = 2.0;
        ROSE.setPrice(ROSE_PRICE * PRICE_MULTIPLIER);

        final double EXPECTED_PRICE = ROSE_PRICE * SMALL_COUNT;
        Assertions.assertEquals(EXPECTED_PRICE, PACK.getPrice(), DELTA);
    }
}
//CHECKSTYLE:ON
