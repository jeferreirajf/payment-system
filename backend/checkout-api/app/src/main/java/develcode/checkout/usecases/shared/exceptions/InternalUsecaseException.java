package develcode.checkout.usecases.shared.exceptions;

public class InternalUsecaseException extends UsecaseException {

    public InternalUsecaseException(final String message) {
        super(message);
    }

    public InternalUsecaseException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
