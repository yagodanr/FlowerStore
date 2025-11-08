package flowerstore_continue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Item {
//CHECKSTYLE:OFF
    protected String description;
//CHECKSTYLE:ON

    public String getDescription() {
        return description;
    }
    public abstract double getPrice();
}
