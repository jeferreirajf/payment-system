package develcode.checkout.domain.valueobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import develcode.checkout.domain.shared.exceptions.ValidationDomainException;

@Testable
public class PaymentDataTest {

    @Test
    public void givenValidParams_whenCreateWith_thenShouldCreateAPaymentData() {
        final var aCardNumber = "1234567890123456";
        final var aCardHolder = "John Doe";
        final var aExpirationDate = "12/2028";
        final var aCvv = "123";

        final var aPaymentData = PaymentData.createWith(aCardNumber, aCardHolder, aExpirationDate, aCvv);

        assertNotNull(aPaymentData);

        assertEquals(aCardNumber, aPaymentData.getCardNumber());
        assertEquals(aCardHolder, aPaymentData.getCardHolder());
        assertEquals(aExpirationDate, aPaymentData.getExpirationDate());
        assertEquals(aCvv, aPaymentData.getCvv());

    }

    @Test
    public void givenNullOrBlankCardNumber_whenCreateWith_thenShouldThrowException() {
        final var aCardNumber = new String[]{"", null};
        final var aCardHolder = "John Doe";
        final var aExpirationDate = "12/2028";
        final var aCvv = "123";

        for (var cardNumber : aCardNumber) {
            final var anException = assertThrows(ValidationDomainException.class,
                    () -> PaymentData.createWith(cardNumber, aCardHolder, aExpirationDate, aCvv));

            assertEquals("Payment card number should not be null or blank", anException.getMessage());
        }
    }

    @Test
    public void givenNullOrBlankCardHolder_whenCreateWith_thenShouldThrowException() {
        final var aCardNumber = "1234567890123456";
        final var aCardHolder = new String[]{"", null};
        final var aExpirationDate = "12/2028";
        final var aCvv = "123";

        for (var cardHolder : aCardHolder) {
            final var anException = assertThrows(ValidationDomainException.class,
                    () -> PaymentData.createWith(aCardNumber, cardHolder, aExpirationDate, aCvv));

            assertEquals("Payment card holder should not be null or blank", anException.getMessage());
        }
    }

    @Test
    public void givenNullOrBlankExpirationDate_whenCreateWith_thenShouldThrowException() {
        final var aCardNumber = "1234567890123456";
        final var aCardHolder = "John Doe";
        final var aExpirationDate = new String[]{"", null};
        final var aCvv = "123";

        for (var expirationDate : aExpirationDate) {
            final var anException = assertThrows(ValidationDomainException.class,
                    () -> PaymentData.createWith(aCardNumber, aCardHolder, expirationDate, aCvv));

            assertEquals("Payment expiration date should not be null or blank", anException.getMessage());
        }
    }

    @Test
    public void givenNullOrBlankCvv_whenCreateWith_thenShouldThrowException() {
        final var aCardNumber = "1234567890123456";
        final var aCardHolder = "John Doe";
        final var aExpirationDate = "12/2028";
        final var aCvv = new String[]{"", null};

        for (var cvv : aCvv) {
            final var anException = assertThrows(ValidationDomainException.class,
                    () -> PaymentData.createWith(aCardNumber, aCardHolder, aExpirationDate, cvv));

            assertEquals("Payment cvv should not be null or blank", anException.getMessage());
        }
    }
}
