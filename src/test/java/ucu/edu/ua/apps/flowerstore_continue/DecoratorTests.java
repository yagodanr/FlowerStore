package ucu.edu.ua.apps.flowerstore_continue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import flowerstore_continue.Item;
import flowerstore_continue.ItemDecorator;
import flowerstore_continue.PaperDecorator;
import flowerstore_continue.RibbonDecorator;

import org.junit.jupiter.api.Assertions;

class DecoratorTests {

    private Item baseItem;

    // Create a simple concrete Item for testing
    static class TestItem extends Item {
        private double price;

        public TestItem(double price, String description) {
            this.price = price;
            this.description = description;
        }

        @Override
        public double getPrice() {
            return price;
        }
    }

    @BeforeEach
    void setUp() {
        baseItem = new TestItem(100.0, "Base Item");
    }

    @Test
    void testPaperDecoratorAddsCorrectPrice() {
        Item decoratedItem = new PaperDecorator(baseItem);

        Assertions.assertEquals(113.0, decoratedItem.getPrice(), 0.01);
    }

    @Test
    void testRibbonDecoratorAddsCorrectPrice() {
        RibbonDecorator decoratedItem = new RibbonDecorator(baseItem);

        Assertions.assertEquals(140.0, decoratedItem.getPrice(), 0.01);
    }

    @Test
    void testPaperDecoratorWithZeroPriceItem() {
        Item freeItem = new TestItem(0.0, "Free Item");
        Item decoratedItem = new PaperDecorator(freeItem);

        Assertions.assertEquals(13.0, decoratedItem.getPrice(), 0.01);
    }

    @Test
    void testRibbonDecoratorWithZeroPriceItem() {
        Item freeItem = new TestItem(0.0, "Free Item");
        RibbonDecorator decoratedItem = new RibbonDecorator(freeItem);

        Assertions.assertEquals(40.0, decoratedItem.getPrice(), 0.01);
    }

    @Test
    void testStackingPaperThenRibbon() {
        Item withPaper = new PaperDecorator(baseItem);
        RibbonDecorator withBoth = new RibbonDecorator(withPaper);

        // Base: 100 + Paper: 13 + Ribbon: 40 = 153
        Assertions.assertEquals(153.0, withBoth.getPrice(), 0.01);
    }

    @Test
    void testStackingRibbonThenPaper() {
        RibbonDecorator withRibbon = new RibbonDecorator(baseItem);
        Item withBoth = new PaperDecorator(withRibbon);

        // Base: 100 + Ribbon: 40 + Paper: 13 = 153
        Assertions.assertEquals(153.0, withBoth.getPrice(), 0.01);
    }

    @Test
    void testMultiplePaperDecorators() {
        Item firstPaper = new PaperDecorator(baseItem);
        Item secondPaper = new PaperDecorator(firstPaper);

        // Base: 100 + Paper: 13 + Paper: 13 = 126
        Assertions.assertEquals(126.0, secondPaper.getPrice(), 0.01);
    }

    @Test
    void testMultipleRibbonDecorators() {
        RibbonDecorator firstRibbon = new RibbonDecorator(baseItem);

        RibbonDecorator secondRibbon = new RibbonDecorator(firstRibbon);

        // Base: 100 + Ribbon: 40 + Ribbon: 40 = 180
        Assertions.assertEquals(180.0, secondRibbon.getPrice(), 0.01);
    }

    @Test
    void testComplexDecoratorChain() {
        // Paper -> Ribbon -> Paper -> Ribbon
        Item step1 = new PaperDecorator(baseItem);

        RibbonDecorator step2 = new RibbonDecorator(step1);

        Item step3 = new PaperDecorator(step2);

        RibbonDecorator step4 = new RibbonDecorator(step3);

        // Base: 100 + Paper: 13 + Ribbon: 40 + Paper: 13 + Ribbon: 40 = 206
        Assertions.assertEquals(206.0, step4.getPrice(), 0.01);
    }

    @Test
    void testDecoratorWithExpensiveItem() {
        Item expensiveItem = new TestItem(999.99, "Expensive Item");
        Item decorated = new PaperDecorator(expensiveItem);

        Assertions.assertEquals(1012.99, decorated.getPrice(), 0.01);
    }

    @Test
    void testPaperDecoratorInheritsFromItemDecorator() {
        Assertions.assertTrue(ItemDecorator.class.isAssignableFrom(PaperDecorator.class));
    }

    @Test
    void testRibbonDecoratorInheritsFromItemDecorator() {
        Assertions.assertTrue(ItemDecorator.class.isAssignableFrom(RibbonDecorator.class));
    }

    @Test
    void testItemDecoratorInheritsFromItem() {
        Assertions.assertTrue(Item.class.isAssignableFrom(ItemDecorator.class));
    }

    @Test
    void testDecoratedItemIsInstanceOfItem() {
        Item decorated = new PaperDecorator(baseItem);
        Assertions.assertTrue(decorated instanceof Item);
    }
}