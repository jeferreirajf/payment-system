package develcode.checkout.domain.valueobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import develcode.checkout.domain.shared.exceptions.ValidationDomainException;

@Testable
public class ItemTest {
    @Test
    public void givenValidParams_whenCreateWith_thenShouldCreateAnItem() {
        final var aName = "Item name";
        final var aQuantity = 1;
        final var aPrice = 2.5f;

        final var anItem = Item.createWith(aName, aQuantity, aPrice);

        assertNotNull(anItem);
        assertEquals(aName, anItem.getName());
        assertEquals(aQuantity, anItem.getQuantity());
        assertEquals(aPrice, anItem.getPrice());
    }

    @Test
    public void givenValidItem_whenCalculateTotal_thenShouldReturnTotalValue() {
        final var aName = "Item name";
        final var aQuantity = 2;
        final var aPrice = 2.5f;

        final var anItem = Item.createWith(aName, aQuantity, aPrice);

        assertEquals(5.0f, anItem.getTotal());
    }

    @Test
    public void givenInvalidName_whenCreateWith_thenShouldThrowException() {
        final var aName = new String[] { "", null };
        final var aQuantity = 1;
        final var aPrice = 2.5f;

        for (var name : aName) {
            final var anException = assertThrows(ValidationDomainException.class,
                    () -> Item.createWith(name, aQuantity, aPrice));

            assertEquals("Item name should not be null or empty", anException.getMessage());
        }
    }

    @Test
    public void givenInvalidPrice_whenCreateWith_thenShouldThrowException() {
        final var aName = "Item name";
        final var aQuantity = 1;
        final var aPrice = new float[] { 0.0f, -1.0f };

        for (var price : aPrice) {
            final var anException = assertThrows(ValidationDomainException.class,
                    () -> Item.createWith(aName, aQuantity, price));

            assertEquals("Item price should not be zero or negative", anException.getMessage());
        }
    }

    @Test
    public void givenInvalidQuantity_whenCreateWith_thenShouldThrowException() {
        final var aName = "Item name";
        final var aQuantity = new int[] { 0, -1 };
        final var aPrice = 2.5f;

        for (var quantity : aQuantity) {
            final var anException = assertThrows(ValidationDomainException.class,
                    () -> Item.createWith(aName, quantity, aPrice));

            assertEquals("Item quantity should not be zero or negative", anException.getMessage());
        }
    }
}
