package develcode.checkout.domain.valueobjects;

import develcode.checkout.domain.shared.valueobjects.ValueObject;
import develcode.checkout.domain.validators.PaymentDataValidator;

public class PaymentData extends ValueObject {

    private final String cardNumber;
    private final String cardHolder;
    private final String expirationDate;
    private final String cvv;

    private PaymentData(final String cardNumber, final String cardHolder, final String expirationDate,
            final String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.validate();
    }

    public static PaymentData createWith(final String cardNumber, final String cardHolder, final String expirationDate,
            final String cvv) {
        return new PaymentData(cardNumber, cardHolder, expirationDate, cvv);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    @Override
    protected void validate() {
        PaymentDataValidator.create().validate(this);
    }

}
