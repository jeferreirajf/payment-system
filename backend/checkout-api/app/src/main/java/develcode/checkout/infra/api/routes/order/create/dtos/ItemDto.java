package develcode.checkout.infra.api.routes.order.create.dtos;

public record ItemDto(
        String name,
        String description,
        float price,
        int quantity) {

}
