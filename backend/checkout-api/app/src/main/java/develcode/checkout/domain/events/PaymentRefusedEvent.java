package develcode.checkout.domain.events;

import develcode.checkout.domain.shared.events.Event;

public class PaymentRefusedEvent extends Event {

    private PaymentRefusedEvent() {
        super("PaymentRejectedEvent");
    }

    public static PaymentRefusedEvent create() {
        return new PaymentRefusedEvent();
    }

    public static PaymentRefusedEvent createWith(final Object aPayload) {
        var event = new PaymentRefusedEvent();
        event.setPayload(aPayload);
        return event;
    }

}
