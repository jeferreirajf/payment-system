package develcode.checkout.domain.events;

import develcode.checkout.domain.shared.events.Event;

public class PaymentPaidEvent extends Event {

    private PaymentPaidEvent() {
        super("PaymentPaidEvent");
    }

    public static PaymentPaidEvent create() {
        return new PaymentPaidEvent();
    }

    public static PaymentPaidEvent createWith(final Object aPayload) {
        var event = new PaymentPaidEvent();
        event.setPayload(aPayload);
        return event;
    }

}
