package develcode.checkout.domain.validators;

import java.util.LinkedList;

import develcode.checkout.domain.shared.exceptions.ValidationDomainException;
import develcode.checkout.domain.shared.validators.Validator;
import develcode.checkout.domain.valueobjects.Item;

public class ItemValidator implements Validator<Item> {

    public static ItemValidator create() {
        return new ItemValidator();
    }

    @Override
    public void validate(final Item item) {

        final var anItemName = item.getName();
        final var anItemPrice = item.getPrice();
        final var anItemQuantity = item.getQuantity();

        var messages = new LinkedList<String>();

        if (anItemName == null || anItemName.isBlank()) {
            messages.add("Item name should not be null or empty");
        }

        if (anItemPrice <= 0) {
            messages.add("Item price should not be zero or negative");
        }

        if (anItemQuantity <= 0) {
            messages.add("Item quantity should not be zero or negative");
        }

        if (!messages.isEmpty()) {
            throw new ValidationDomainException(String.join(". ", messages));
        }

    }
}
