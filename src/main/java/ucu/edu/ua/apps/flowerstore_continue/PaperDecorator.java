package flowerstore_continue;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PaperDecorator extends ItemDecorator {
    public PaperDecorator(Item item) {
        this.item = item;
    }

//CHECKSTYLE:OFF
    @Override
    public double getPrice() {
        return 13 + item.getPrice();
    }
//CHECKSTYLE:ON

}
