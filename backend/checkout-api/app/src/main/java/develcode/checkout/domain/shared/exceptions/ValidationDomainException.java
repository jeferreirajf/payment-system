package develcode.checkout.domain.shared.exceptions;

public class ValidationDomainException extends DomainException {

    public ValidationDomainException(String message) {
        super(message);
    }

    public ValidationDomainException(String message, Throwable cause) {
        super(message, cause);
    }

}
