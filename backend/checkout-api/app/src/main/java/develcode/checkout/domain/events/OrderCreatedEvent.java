package develcode.checkout.domain.events;

import develcode.checkout.domain.shared.events.Event;

public class OrderCreatedEvent extends Event {

    private OrderCreatedEvent() {
        super("OrderCreatedEvent");
    }

    public static OrderCreatedEvent create() {
        return new OrderCreatedEvent();
    }

    public static OrderCreatedEvent createWith(final Object aPayload) {
        var event = new OrderCreatedEvent();
        event.setPayload(aPayload);
        return event;
    }
}
