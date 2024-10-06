package develcode.checkout.infra.api.routes.order.list.dtos;

public record ItemDto(
        String name,
        float price,
        int quantity) {

}
