package flowerstore_continue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Item {
    protected String description;

    public String getDescription() {
        return description;
    }
    public abstract double getPrice();
}
