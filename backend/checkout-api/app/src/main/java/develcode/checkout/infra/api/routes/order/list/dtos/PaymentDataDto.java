package develcode.checkout.infra.api.routes.order.list.dtos;

public record PaymentDataDto(
        String cardNumber,
        String cardHolderName,
        String cardExpirationDate,
        String cardCvv) {

}
