package develcode.checkout.domain.validators;

import java.util.LinkedList;

import develcode.checkout.domain.shared.exceptions.ValidationDomainException;
import develcode.checkout.domain.shared.validators.Validator;
import develcode.checkout.domain.valueobjects.Customer;

public class CustomerValidator implements Validator<Customer> {

    public static CustomerValidator create() {
        return new CustomerValidator();
    }

    @Override
    public void validate(final Customer customer) {
        final var aName = customer.getName();
        final var anEmail = customer.getEmail();

        final var messages = new LinkedList<String>();

        if (aName == null || aName.isBlank()) {
            messages.add("Customer name should not be null or blank");
        }

        final var emailRegexPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        if (anEmail == null || !anEmail.matches(emailRegexPattern)) {
            messages.add("Customer email should be a valid email");
        }

        if (!messages.isEmpty()) {
            throw new ValidationDomainException(String.join(". ", messages));
        }
    }

}
