package develcode.checkout.infra.repositories.jpa.order.mappers;

import java.util.function.Function;

import develcode.checkout.domain.entities.Order;
import develcode.checkout.infra.repositories.jpa.order.models.OrderJpaModel;

public class OrderModelToEntityMapper implements Function<OrderJpaModel, Order> {

    public static Order map(final OrderJpaModel aModel) {
        return new OrderModelToEntityMapper().apply(aModel);
    }

    @Override
    public Order apply(final OrderJpaModel aModel) {
        final var aCustomer = CustomerModelToValueObjectMapper.map(aModel.getCustomer());
        final var aPaymentData = PaymentDataModelToValueObjectMapper.map(aModel.getPaymentData());
        final var someItems = aModel.getItems().stream()
                .map(OrderItemModelToValueObjectMapper::map)
                .toList();

        final var anOrder = Order.createWith(
                aModel.getId(),
                aCustomer,
                aPaymentData,
                someItems,
                aModel.getStatus(),
                aModel.getCreatedAt(),
                aModel.getUpdatedAt()
        );

        return anOrder;

    }

}
