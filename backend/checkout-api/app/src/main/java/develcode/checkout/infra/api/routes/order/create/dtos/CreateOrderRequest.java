package develcode.checkout.infra.api.routes.order.create.dtos;

public record CreateOrderRequest(
        CustomerDto customer,
        PaymentDataDto paymentData,
        ItemDto item) {

}
