package ucu.edu.ua.apps;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @AllArgsConstructor
public class Flower {
    private double sepalLength;
    private FlowerColor color;
    private double price;
    private FlowerType flowerType;

    public Flower() {
        sepalLength = 0.0;
        color = FlowerColor.WHITE;
        price = 0.0;
        flowerType = FlowerType.ROSE;
    }

    public Flower(Flower flower) {
        sepalLength = flower.sepalLength;
        color = flower.color;
        price = flower.price;
        flowerType = flower.flowerType;
    }

    public String getColor() {
        return color.toString();
    }

    public boolean equals(Flower flower) {
        if(sepalLength != flower.sepalLength) {
            return false;
        }
        if(color != flower.color) {
            return false;
        }
        if(price != flower.price) {
            return false;
        }
        if(flowerType != flower.flowerType) {
            return false;
        }

        return true;
    }
}
