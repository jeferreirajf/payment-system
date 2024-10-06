package develcode.checkout.handlers.shared.exceptions;

public class BadRequestEventHandlerException extends EventHandlerException {

    public BadRequestEventHandlerException(final String message) {
        super(message);
    }

    public BadRequestEventHandlerException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
