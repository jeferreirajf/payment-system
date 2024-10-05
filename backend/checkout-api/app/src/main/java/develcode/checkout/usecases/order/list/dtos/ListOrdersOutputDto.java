package develcode.checkout.usecases.order.list.dtos;

import java.util.List;

import develcode.checkout.domain.entities.Order;

public record ListOrdersOutputDto(
        List<OrderDto> orders) {

    public static ListOrdersOutputDto from(final List<Order> orders) {
        return new ListOrdersOutputDto(
                orders.stream().map(OrderDto::from).toList()
        );
    }
}
