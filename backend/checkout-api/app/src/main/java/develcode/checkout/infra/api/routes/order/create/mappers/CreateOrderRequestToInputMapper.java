package develcode.checkout.infra.api.routes.order.create.mappers;

import java.util.function.Function;

import develcode.checkout.infra.api.routes.order.create.dtos.CreateOrderRequest;
import develcode.checkout.usecases.order.create.dtos.CreateOrderInputDto;
import develcode.checkout.usecases.order.create.dtos.CustomerDto;
import develcode.checkout.usecases.order.create.dtos.PaymentDataDto;
import develcode.checkout.usecases.order.create.dtos.ProductDto;

public class CreateOrderRequestToInputMapper implements Function<CreateOrderRequest, CreateOrderInputDto> {

    public static CreateOrderInputDto map(CreateOrderRequest request) {
        return new CreateOrderRequestToInputMapper().apply(request);
    }

    @Override
    public CreateOrderInputDto apply(CreateOrderRequest request) {

        final var customer = new CustomerDto(request.customer().name(),
                request.customer().email());

        final var paymentData = new PaymentDataDto(
                request.paymentData().cardNumber(),
                request.paymentData().cardHolderName(),
                request.paymentData().cardExpirationDate(),
                request.paymentData().cardCvv()
        );

        final var produt = new ProductDto(
                request.item().name(),
                request.item().description(),
                request.item().price(),
                request.item().quantity()
        );

        final var input = new CreateOrderInputDto(
                customer,
                paymentData,
                produt
        );

        return input;

    }

}
