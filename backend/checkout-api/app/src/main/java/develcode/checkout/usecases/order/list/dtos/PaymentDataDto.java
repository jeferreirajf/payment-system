package develcode.checkout.usecases.order.list.dtos;

import develcode.checkout.domain.valueobjects.PaymentData;

public record PaymentDataDto(
        String paymentMethod,
        String cardNumber,
        String cardHolder,
        String cardExpirationDate,
        String cardCvv) {

    public static PaymentDataDto from(final PaymentData paymentData) {
        return new PaymentDataDto(
                "card",
                paymentData.getCardNumber(),
                paymentData.getCardHolder(),
                paymentData.getExpirationDate(),
                paymentData.getCvv());
    }
}
