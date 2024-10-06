package develcode.checkout.infra.repositories.jpa.order.mappers;

import java.util.function.Function;

import develcode.checkout.domain.valueobjects.Item;
import develcode.checkout.infra.repositories.jpa.order.models.OrderItemJpaModel;

public class OrderItemModelToValueObjectMapper implements Function<OrderItemJpaModel, Item> {

    public static Item map(final OrderItemJpaModel aModel) {
        return new OrderItemModelToValueObjectMapper().apply(aModel);
    }

    @Override
    public Item apply(final OrderItemJpaModel aModel) {
        final var anItem = Item.createWith(
                aModel.getName(),
                aModel.getQuantity(),
                aModel.getPrice()
        );

        return anItem;
    }

}
