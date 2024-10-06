package develcode.checkout.infra.api.routes.order.list.dtos;

import java.util.List;
import java.time.Instant;

public record OrderDto(
        String orderId,
        CustomerDto customer,
        PaymentDataDto paymentData,
        List<ItemDto> items,
        String status,
        Instant createdAt,
        Instant updatedAt) {

}
