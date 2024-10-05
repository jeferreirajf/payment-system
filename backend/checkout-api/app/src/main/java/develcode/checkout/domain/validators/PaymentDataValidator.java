package develcode.checkout.domain.validators;

import java.util.LinkedList;

import develcode.checkout.domain.shared.exceptions.ValidationDomainException;
import develcode.checkout.domain.shared.validators.Validator;
import develcode.checkout.domain.valueobjects.PaymentData;

public class PaymentDataValidator implements Validator<PaymentData> {

    public static PaymentDataValidator create() {
        return new PaymentDataValidator();
    }

    @Override
    public void validate(final PaymentData paymentData) {
        final var aCardNumber = paymentData.getCardNumber();
        final var aCardHolder = paymentData.getCardHolder();
        final var anExpirationDate = paymentData.getExpirationDate();
        final var aCvv = paymentData.getCvv();

        final var messages = new LinkedList<String>();

        if (aCardNumber == null || aCardNumber.isBlank()) {
            messages.add("Payment card number should not be null or blank");
        }

        if (aCardHolder == null || aCardHolder.isBlank()) {
            messages.add("Payment card holder should not be null or blank");
        }

        if (anExpirationDate == null || anExpirationDate.isBlank()) {
            messages.add("Payment expiration date should not be null or blank");
        }

        if (aCvv == null || aCvv.isBlank()) {
            messages.add("Payment cvv should not be null or blank");
        }

        if (!messages.isEmpty()) {
            throw new ValidationDomainException(String.join(". ", messages));
        }
    }

}
