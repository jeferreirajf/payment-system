package develcode.checkout.handlers.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import develcode.checkout.domain.gateways.OrderGateway;
import develcode.checkout.domain.shared.events.Event;
import develcode.checkout.domain.valueobjects.OrderStatus;
import develcode.checkout.handlers.EventHandler;
import develcode.checkout.handlers.shared.exceptions.BadRequestEventHandlerException;
import develcode.checkout.handlers.shared.exceptions.InternalEventHandlerException;
import develcode.checkout.handlers.shared.exceptions.NotFoundEventHandlerException;

public class PaymentRefusedEventHandler implements EventHandler {

    private final OrderGateway orderGateway;
    private final Logger logger;

    private PaymentRefusedEventHandler(final OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
        this.logger = LoggerFactory.getLogger(PaymentRefusedEventHandler.class.getName());
    }

    public static PaymentRefusedEventHandler create(final OrderGateway orderGateway) {

        if (orderGateway == null) {
            throw new InternalEventHandlerException(
                    "The orderGateway dependency must be provided while creating PaymentPaidEventHandler"
            );

        }

        return new PaymentRefusedEventHandler(orderGateway);
    }

    @Override
    public void handle(final Event event) {
        try {
            final var payload = event.getPayload(PaymentRefusedEventPayload.class);

            final var anOrder = orderGateway.findById(payload.orderId());

            if (anOrder == null) {
                throw new NotFoundEventHandlerException(
                        "The order with id " + payload.orderId() + " was not found while handling PaymentPaidEvent"
                );
            }

            if (OrderStatus.REJECTED.toString().equals(payload.status)) {
                anOrder.reject();
                orderGateway.update(anOrder);
            } else {
                throw new BadRequestEventHandlerException(
                        "The payment with id " + payload.paymentId() + " was " + payload.status + " instead of REJECTED while handling PaymentRefusedEvent"
                );
            }

        } catch (ClassCastException e) {
            this.logger.error("The event payload is not a PaymentEventPayload instance", e);
        } catch (Exception e) {
            this.logger.error("An error occurred while handling PaymentPaidEvent", e);
        }

    }

    private record PaymentRefusedEventPayload(String orderId, String paymentId, String status) {

    }

}
