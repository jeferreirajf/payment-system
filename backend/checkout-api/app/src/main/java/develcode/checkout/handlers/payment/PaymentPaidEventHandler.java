package develcode.checkout.handlers.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

import develcode.checkout.domain.gateways.OrderGateway;
import develcode.checkout.domain.shared.events.Event;
import develcode.checkout.domain.valueobjects.OrderStatus;
import develcode.checkout.handlers.EventHandler;
import develcode.checkout.handlers.shared.exceptions.BadRequestEventHandlerException;
import develcode.checkout.handlers.shared.exceptions.InternalEventHandlerException;
import develcode.checkout.handlers.shared.exceptions.NotFoundEventHandlerException;

public class PaymentPaidEventHandler implements EventHandler {

    private final OrderGateway orderGateway;
    private final Logger logger;

    private PaymentPaidEventHandler(final OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
        this.logger = LoggerFactory.getLogger(PaymentRefusedEventHandler.class.getName());
    }

    public static PaymentPaidEventHandler create(final OrderGateway orderGateway) {

        if (orderGateway == null) {
            throw new InternalEventHandlerException(
                    "The orderGateway dependency must be provided while creating PaymentPaidEventHandler"
            );

        }

        return new PaymentPaidEventHandler(orderGateway);
    }

    @Override
    public void handle(final Event event) {
        try {
            final var payload = event.getPayload(PaymentPaidEventPayload.class);

            final var anOrder = orderGateway.findById(payload.orderId);

            if (anOrder == null) {
                throw new NotFoundEventHandlerException(
                        "The order with id " + payload.orderId + " was not found while handling PaymentPaidEvent"
                );
            }

            if (OrderStatus.APPROVED.toString().equals(payload.status)) {
                anOrder.approve();
                orderGateway.update(anOrder);
            } else {
                throw new BadRequestEventHandlerException(
                        "The payment with id " + payload.paymentId + " was " + payload.status + " instead of APPROVED while handling PaymentPaidEvent"
                );
            }

        } catch (ClassCastException e) {
            this.logger.error("The event payload is not a PaymentEventPayload instance", e);
        } catch (Exception e) {
            this.logger.error("An error occurred while handling PaymentPaidEvent", e);
        }

    }

    private class PaymentPaidEventPayload {

        @JsonProperty("orderId")
        public String orderId;
        @JsonProperty("paymentId")
        public String paymentId;
        @JsonProperty("status")
        public String status;

        public PaymentPaidEventPayload() {
        }
    }

}
