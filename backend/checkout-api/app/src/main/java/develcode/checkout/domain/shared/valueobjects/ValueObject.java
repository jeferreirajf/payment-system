package develcode.checkout.domain.shared.valueobjects;

public abstract class ValueObject {
    protected ValueObject() {
    }

    protected abstract void validate();
}
