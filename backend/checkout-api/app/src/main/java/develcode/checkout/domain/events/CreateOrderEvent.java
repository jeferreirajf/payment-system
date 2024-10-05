package develcode.checkout.domain.events;

import develcode.checkout.domain.shared.events.Event;

public class CreateOrderEvent extends Event {

    private CreateOrderEvent() {
        super("CreateOrderEvent");
    }

    public static CreateOrderEvent create() {
        return new CreateOrderEvent();
    }

    public static CreateOrderEvent createWith(final Object aPayload) {
        var event = new CreateOrderEvent();
        event.setPayload(aPayload);
        return event;
    }
}
