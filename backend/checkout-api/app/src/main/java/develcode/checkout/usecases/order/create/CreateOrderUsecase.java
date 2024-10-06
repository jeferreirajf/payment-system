package develcode.checkout.usecases.order.create;

import develcode.checkout.domain.entities.Order;
import develcode.checkout.domain.events.OrderCreatedEvent;
import develcode.checkout.domain.gateways.OrderGateway;
import develcode.checkout.domain.valueobjects.Customer;
import develcode.checkout.domain.valueobjects.Item;
import develcode.checkout.domain.valueobjects.PaymentData;
import develcode.checkout.infra.messaging.Messaging;
import develcode.checkout.usecases.Usecase;
import develcode.checkout.usecases.order.create.dtos.CreateOrderInputDto;
import develcode.checkout.usecases.shared.exceptions.InternalUsecaseException;

public class CreateOrderUsecase implements Usecase<CreateOrderInputDto, String> {

    private final Messaging messaging;
    private final OrderGateway orderGateway;

    private CreateOrderUsecase(final Messaging messaging, final OrderGateway orderGateway) {
        this.messaging = messaging;
        this.orderGateway = orderGateway;
    }

    public static CreateOrderUsecase create(final OrderGateway orderGateway, final Messaging messaging) {
        if (messaging == null || orderGateway == null) {
            throw new InternalUsecaseException(
                    "The messaging and orderGateway dependencies must be provided while creating CreateOrderUsecase"
            );
        }

        return new CreateOrderUsecase(messaging, orderGateway);
    }

    @Override
    public String execute(final CreateOrderInputDto input) {
        final var aCustomer = Customer.createWith(
                input.customer().name(),
                input.customer().email());

        final var aPaymentData = PaymentData.createWith(
                input.paymentData().cardNumber(),
                input.paymentData().cardHolderName(),
                input.paymentData().cardExpirationDate(),
                input.paymentData().cardCvv());

        final var anOrderItem = Item.createWith(
                input.product().name(),
                input.product().quantity(),
                input.product().price());

        final var anOrder = Order.create(aCustomer, aPaymentData);

        anOrder.addItem(anOrderItem);

        orderGateway.create(anOrder);

        final var event = OrderCreatedEvent.createWith(anOrder);

        messaging.publish("payment", event);

        return anOrder.getId();
    }
}
