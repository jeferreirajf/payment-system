package develcode.checkout.domain.validators;

import java.util.LinkedList;

import develcode.checkout.domain.entities.Order;
import develcode.checkout.domain.shared.exceptions.ValidationDomainException;
import develcode.checkout.domain.shared.validators.Validator;

public class OrderValidator implements Validator<Order> {

    public static OrderValidator create() {
        return new OrderValidator();
    }

    @Override
    public void validate(final Order order) {
        final var id = order.getId();
        final var customer = order.getCustomer();
        final var paymentData = order.getPaymentData();
        final var items = order.getItems();
        final var status = order.getStatus();
        final var createdAt = order.getCreatedAt();
        final var updatedAt = order.getUpdatedAt();

        final var messages = new LinkedList<String>();

        final var uuidRegex = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";

        if (id == null || id.isBlank() || !id.matches(uuidRegex)) {
            messages.add("Order id should be a valid UUID");
        }

        if (customer == null) {
            messages.add("Order customer should not be null");
        }

        if (paymentData == null) {
            messages.add("Order payment data should not be null");
        }

        if (items == null) {
            messages.add("Order items should not be null");
        }

        if (status == null) {
            messages.add("Order status should not be null");
        }

        if (createdAt == null) {
            messages.add("Order created at should not be null");
        }

        if (updatedAt == null) {
            messages.add("Order updated at should not be null");
        }

        if (createdAt != null && createdAt.isAfter(updatedAt)) {
            messages.add("Order created at should not be after updated at");
        }

        if (!messages.isEmpty()) {
            throw new ValidationDomainException(String.join(". ", messages));
        }
    }
}
