package develcode.checkout.infra.api.routes.order.create.dtos;

public record PaymentDataDto(
        String cardNumber,
        String cardHolderName,
        String cardExpirationDate,
        String cardCvv) {

}
