package ucu.edu.ua.apps;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FlowerPack {
    private Flower flower;
    private int count;

    public FlowerPack(Flower flowerInstance, int count) {
        this.flower = new Flower(flowerInstance);
        this.count = count;
    }

    public double getPrice() {
        return flower.getPrice() * count;
    }

    public boolean contains(Flower flower) {
        return this.flower.equals(flower);
    }
}
