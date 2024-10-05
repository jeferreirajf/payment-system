package develcode.checkout.handlers;

import develcode.checkout.domain.shared.events.Event;

public interface EventHandler {

    public void handle(final Event event);
}
