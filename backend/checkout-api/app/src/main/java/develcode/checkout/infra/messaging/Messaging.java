package develcode.checkout.infra.messaging;

import develcode.checkout.domain.shared.events.Event;
import develcode.checkout.handlers.EventHandler;

public interface Messaging {

    public void publish(final Event event);

    public void subscribe(final String eventName, final EventHandler handler);

}
