package develcode.checkout.domain.events;

import develcode.checkout.domain.entities.Order;
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

    @Override
    public String toString() {
        return "OrderCreatedEvent{"
                + "id=" + getId()
                + ", name=" + getName()
                + ", payload=" + getPayload(Order.class).toString()
                + '}';
    }
}
