package ucu.edu.ua.apps;

import java.util.ArrayList;
import java.util.List;


public class FlowerBucket {
    private List<FlowerPack> flowerPacks = new ArrayList<>();

    public double getPrice() {
        double price = 0;

        for (FlowerPack flowerPack : flowerPacks) {
            price += flowerPack.getPrice();
        }
        return price;
    }

    public void add(FlowerPack flowerPack) {
        flowerPacks.add(flowerPack);
    }

    public boolean contains(Flower flower) {
        for (FlowerPack flowerPack : flowerPacks) {
            if(flowerPack.contains(flower)) {
                return true;
            }
        }
        return false;
    }
}
