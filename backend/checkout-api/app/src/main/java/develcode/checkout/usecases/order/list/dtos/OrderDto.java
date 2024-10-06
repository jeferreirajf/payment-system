package develcode.checkout.usecases.order.list.dtos;

import java.time.Instant;
import java.util.List;

import develcode.checkout.domain.entities.Order;

public record OrderDto(
        String orderId,
        CustomerDto customer,
        List<ItemDto> items,
        PaymentDataDto paymentData,
        float totalValue,
        String status,
        Instant createdAt,
        Instant updatedAt) {

    public static OrderDto from(final Order order) {
        return new OrderDto(
                order.getId(),
                CustomerDto.from(order.getCustomer()),
                order.getItems().stream().map(ItemDto::from).toList(),
                PaymentDataDto.from(order.getPaymentData()),
                order.getTotal(),
                order.getStatus().name(),
                order.getCreatedAt(),
                order.getUpdatedAt()
        );
    }

}
