package develcode.checkout.domain.shared.exceptions;

public class BusinessDomainException extends DomainException {

    public BusinessDomainException(String message) {
        super(message);
    }

    public BusinessDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
