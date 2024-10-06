package develcode.checkout.infra.handlers;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import develcode.checkout.domain.events.PaymentPaidEvent;
import develcode.checkout.domain.events.PaymentRefusedEvent;
import develcode.checkout.domain.gateways.OrderGateway;
import develcode.checkout.handlers.payment.PaymentPaidEventHandler;
import develcode.checkout.handlers.payment.PaymentRefusedEventHandler;
import develcode.checkout.infra.messaging.Messaging;

@Configuration
public class EventHandlerBootstrap implements InitializingBean {

    @Autowired
    private Messaging messaging;

    @Autowired
    private OrderGateway orderGateway;

    @Override
    public void afterPropertiesSet() {
        final var paymentPaidEventHandler = PaymentPaidEventHandler.create(this.orderGateway);
        final var paymentRefusedEventHandler = PaymentRefusedEventHandler.create(this.orderGateway);

        this.messaging.subscribe(PaymentPaidEvent.create().getName(), paymentPaidEventHandler);
        this.messaging.subscribe(PaymentRefusedEvent.create().getName(), paymentRefusedEventHandler);
    }
}
