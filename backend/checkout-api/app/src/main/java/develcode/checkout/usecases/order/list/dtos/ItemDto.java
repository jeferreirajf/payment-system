package develcode.checkout.usecases.order.list.dtos;

import develcode.checkout.domain.valueobjects.Item;

public record ItemDto(
        String name,
        Integer quantity,
        float price,
        float totalValue) {

    public static ItemDto from(final Item item) {
        return new ItemDto(
                item.getName(),
                item.getQuantity(),
                item.getPrice(),
                item.getTotal()
        );
    }
}
