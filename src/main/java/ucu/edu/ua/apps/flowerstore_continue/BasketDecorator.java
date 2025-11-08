package flowerstore_continue;

public class BasketDecorator extends ItemDecorator {
    public BasketDecorator(Item item) {
        this.item = item;
    }

    @Override
    public double getPrice() {
        return 4 + item.getPrice();
    }
}
