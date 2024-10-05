package develcode.checkout.usecases.order.create.dtos;

public record CreateOrderInputDto(
        CustomerDto customer,
        PaymentDataDto paymentData,
        ProductDto product) {

}
