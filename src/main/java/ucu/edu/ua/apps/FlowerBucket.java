package ucu.edu.ua.apps;

import java.util.List;


public class FlowerBucket {
    private List<FlowerPack> flowerPacks;

    public double getPrice() {
        double price = 0;

        for (FlowerPack flowerPack : flowerPacks) {
            price += flowerPack.getPrice();
        }
        return price;
    }
}
