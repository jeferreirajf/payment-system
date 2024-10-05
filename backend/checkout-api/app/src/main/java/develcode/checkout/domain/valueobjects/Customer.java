package develcode.checkout.domain.valueobjects;

import develcode.checkout.domain.shared.valueobjects.ValueObject;
import develcode.checkout.domain.validators.CustomerValidator;

public class Customer extends ValueObject {

    private final String name;
    private final String email;

    private Customer(final String name, final String email) {
        this.name = name;
        this.email = email;
        this.validate();
    }

    public static Customer createWith(final String aName, final String anEmail) {
        return new Customer(aName, anEmail);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    protected void validate() {
        CustomerValidator.create().validate(this);
    }

}
