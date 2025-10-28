package flowers;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "flowers")
public class Flower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double sepalLength;
    @Enumerated(EnumType.STRING)
    private FlowerColor flowerColor;
    private double price;
    @Enumerated(EnumType.STRING)
    private FlowerType flowerType;


    public Flower(Flower flower) {
        sepalLength = flower.sepalLength;
        flowerColor = flower.flowerColor;
        price = flower.price;
        flowerType = flower.flowerType;
    }

    public String getColor() {
        return flowerColor.toString();
    }

    public boolean equals(Flower flower) {
        if (sepalLength != flower.sepalLength) {
            return false;
        }
        if (flowerColor != flower.flowerColor) {
            return false;
        }
        if (price != flower.price) {
            return false;
        }
        if (flowerType != flower.flowerType) {
            return false;
        }

        return true;
    }
    public boolean equals(Object o) {
        return false;
    }
}
