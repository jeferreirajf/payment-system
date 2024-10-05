package develcode.checkout.domain.valueobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import develcode.checkout.domain.shared.exceptions.ValidationDomainException;

@Testable
public class CustomerTest {

    @Test
    public void givenValidParams_whenCreateWith_thenShouldCreateACustomer() {
        final var aName = "Customer name";
        final var anEmail = "john@doe.com";

        final var aCustomer = Customer.createWith(aName, anEmail);

        assertNotNull(aCustomer);

        assertEquals(aName, aCustomer.getName());
        assertEquals(anEmail, aCustomer.getEmail());
    }

    @Test
    public void givenInvalidName_whenCreateWith_thenShouldThrowException() {
        final var aName = new String[] { "", null };
        final var anEmail = "john@doe.com";

        for (var name : aName) {
            final var anException = assertThrows(ValidationDomainException.class,
                    () -> Customer.createWith(name, anEmail));

            assertEquals("Customer name should not be null or blank", anException.getMessage());
        }
    }

    @Test
    public void givenInvalidEmail_whenCreateWith_thenShouldThrowException() {
        final var aName = "Customer name";
        final var anEmail = new String[] { "", null, "john@doe", "john@doe.", "john", "@doe.com", "@doe" };

        for (var email : anEmail) {
            final var anException = assertThrows(ValidationDomainException.class,
                    () -> Customer.createWith(aName, email));

            assertEquals("Customer email should be a valid email", anException.getMessage());
        }
    }

}
