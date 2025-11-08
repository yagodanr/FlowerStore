package flowerstore_continue;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RibbonDecorator extends ItemDecorator {
    public RibbonDecorator(Item item) {
        this.item = item;
    }
    //CHECKSTYLE:OFF
    @Override
    public double getPrice() {
        return 40 + item.getPrice();
    }
    //CHECKSTYLE:ON

}
