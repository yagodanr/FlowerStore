package ucu.edu.ua.apps;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<FlowerBucket> flowerBuckets = new ArrayList<>();

    public List<FlowerBucket> search(int price) {
        List<FlowerBucket> matched = new ArrayList<>();

        for (FlowerBucket flowerBucket : flowerBuckets) {
            if (flowerBucket.getPrice() <= price) {
                matched.add(flowerBucket);
            }
        }

        return matched;
    }

    public List<FlowerBucket> search(Flower flower) {
        List<FlowerBucket> matched = new ArrayList<>();
        for (FlowerBucket flowerBucket : flowerBuckets) {
            if (flowerBucket.contains(flower)) {
                matched.add(flowerBucket);
            }
        }

        return matched;
    }

    public void add(FlowerBucket bucket) {
        flowerBuckets.add(bucket);
    }
}
