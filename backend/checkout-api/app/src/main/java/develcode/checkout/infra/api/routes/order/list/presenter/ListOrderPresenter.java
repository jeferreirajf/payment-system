package develcode.checkout.infra.api.routes.order.list.presenter;

import develcode.checkout.infra.api.routes.order.list.dtos.PaymentDataDto;
import develcode.checkout.infra.api.routes.order.list.dtos.CustomerDto;
import develcode.checkout.infra.api.routes.order.list.dtos.ItemDto;
import develcode.checkout.infra.api.routes.order.list.dtos.ListOrderResponse;
import develcode.checkout.infra.api.routes.order.list.dtos.OrderDto;
import develcode.checkout.usecases.order.list.dtos.ListOrdersOutputDto;

import java.util.stream.Collectors;

public class ListOrderPresenter {

    public static ListOrderResponse present(final ListOrdersOutputDto output) {
        final var orders = output.orders().stream().map(order -> {
            final var customer = new CustomerDto(
                    order.customer().name(),
                    order.customer().email());

            final var paymentData = new PaymentDataDto(
                    order.paymentData().cardNumber(),
                    order.paymentData().cardHolder(),
                    order.paymentData().cardExpirationDate(),
                    order.paymentData().cardCvv());

            final var items = order.items().stream().map(item -> {
                return new ItemDto(item.name(), item.price(), item.quantity());
            }).collect(Collectors.toList());

            return new OrderDto(
                    order.orderId(),
                    customer,
                    paymentData,
                    items,
                    order.status(),
                    order.createdAt(),
                    order.updatedAt()
            );
        }).collect(Collectors.toList());

        final var response = new ListOrderResponse(orders);

        return response;
    }

}
