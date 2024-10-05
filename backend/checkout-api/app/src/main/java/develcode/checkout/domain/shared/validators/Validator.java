package develcode.checkout.domain.shared.validators;

public interface Validator<T> {
    void validate(T input);
}
