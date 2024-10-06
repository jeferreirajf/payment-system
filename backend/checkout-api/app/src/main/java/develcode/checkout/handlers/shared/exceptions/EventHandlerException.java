package develcode.checkout.handlers.shared.exceptions;

public class EventHandlerException extends RuntimeException {

    public EventHandlerException(final String message) {
        super(message);
    }

    public EventHandlerException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
