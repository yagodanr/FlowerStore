package flowerstore_continue;

import flowers.Flower;

public class Item {
    private Flower flower;
    private int count;

    public Item(Flower flowerInstance, int count) {
        this.flower = new Flower(flowerInstance);
        this.count = count;
    }

    public double getPrice() {
        return flower.getPrice() * count;
    }
}
