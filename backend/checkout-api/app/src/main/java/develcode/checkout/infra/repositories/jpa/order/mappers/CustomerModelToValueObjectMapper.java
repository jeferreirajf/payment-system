package develcode.checkout.infra.repositories.jpa.order.mappers;

import java.util.function.Function;

import develcode.checkout.domain.valueobjects.Customer;
import develcode.checkout.infra.repositories.jpa.order.models.CustomerJpaModel;

public class CustomerModelToValueObjectMapper implements Function<CustomerJpaModel, Customer> {

    public static Customer map(final CustomerJpaModel aModel) {
        return new CustomerModelToValueObjectMapper().apply(aModel);
    }

    @Override
    public Customer apply(final CustomerJpaModel aModel) {
        final var aCustomer = Customer.createWith(aModel.getName(), aModel.getEmail());

        return aCustomer;
    }

}
