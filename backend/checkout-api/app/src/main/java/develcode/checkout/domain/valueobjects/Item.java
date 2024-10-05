package develcode.checkout.domain.valueobjects;

import develcode.checkout.domain.shared.valueobjects.ValueObject;
import develcode.checkout.domain.validators.ItemValidator;

public class Item extends ValueObject {

    private String name;
    private int quantity;
    private float price;

    private Item(String name, int quantity, float price) {
        super();
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.validate();
    }

    public static Item createWith(String aName, int aQuantity, float aPrice) {
        return new Item(aName, aQuantity, aPrice);
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public float getTotal() {
        return this.price * this.quantity;
    }

    @Override
    protected void validate() {
        ItemValidator.create().validate(this);
    }
}
