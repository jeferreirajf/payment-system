package develcode.checkout.handlers.shared.exceptions;

public class NotFoundEventHandlerException extends EventHandlerException {

    public NotFoundEventHandlerException(final String message) {
        super(message);
    }

    public NotFoundEventHandlerException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
