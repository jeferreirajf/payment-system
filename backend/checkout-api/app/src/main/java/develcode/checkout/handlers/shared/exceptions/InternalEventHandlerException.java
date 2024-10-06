package develcode.checkout.handlers.shared.exceptions;

public class InternalEventHandlerException extends EventHandlerException {

    public InternalEventHandlerException(final String message) {
        super(message);
    }

    public InternalEventHandlerException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
