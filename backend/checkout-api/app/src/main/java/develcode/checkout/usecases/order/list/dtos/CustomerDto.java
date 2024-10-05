package develcode.checkout.usecases.order.list.dtos;

import develcode.checkout.domain.valueobjects.Customer;

public record CustomerDto(
        String name,
        String email) {

    public static CustomerDto from(final Customer customer) {
        return new CustomerDto(customer.getName(), customer.getEmail());
    }
}
