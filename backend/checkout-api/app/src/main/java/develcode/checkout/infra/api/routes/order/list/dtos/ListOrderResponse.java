package develcode.checkout.infra.api.routes.order.list.dtos;

import java.util.List;

public record ListOrderResponse(
        List<OrderDto> orders) {

}
