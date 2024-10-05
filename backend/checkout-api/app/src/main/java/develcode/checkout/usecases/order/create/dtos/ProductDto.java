package develcode.checkout.usecases.order.create.dtos;

public record ProductDto(
        String name,
        String description,
        float price,
        int quantity) {

}
