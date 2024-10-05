package develcode.checkout.usecases.shared.exceptions;

public class UsecaseException extends RuntimeException {

    public UsecaseException(final String message) {
        super(message);
    }

    public UsecaseException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
