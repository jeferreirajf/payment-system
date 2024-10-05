package develcode.checkout.usecases.order.create.dtos;

public record PaymentDataDto(
        String cardNumber,
        String cardHolderName,
        String cardExpirationDate,
        String cardCvv) {

}
