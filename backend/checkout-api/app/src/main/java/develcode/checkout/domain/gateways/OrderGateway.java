package develcode.checkout.domain.gateways;

import develcode.checkout.domain.entities.Order;

public interface OrderGateway {

    void create(final Order anOrder);

    Order findById(final String id);

    Order[] list();
}
