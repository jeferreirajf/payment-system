package develcode.checkout.infra.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import develcode.checkout.domain.entities.Order;
import develcode.checkout.domain.valueobjects.Customer;
import develcode.checkout.domain.valueobjects.Item;
import develcode.checkout.domain.valueobjects.PaymentData;
import develcode.checkout.infra.annotations.JpaIntegrationTest;
import develcode.checkout.infra.repositories.jpa.order.OrderJpaGateway;
import develcode.checkout.infra.repositories.jpa.order.OrderJpaRepository;

@JpaIntegrationTest
public class OrderJpaGatewayIntegrationTest {

    @Autowired
    private OrderJpaGateway orderGateway;

    @Autowired
    private OrderJpaRepository orderRepository;

    private Order order;

    @BeforeEach
    public void setUp() {
        final var aCustomer = Customer.createWith("John Doe", "john@doe.com");
        final var aPaymentData = PaymentData.createWith("1234567890123456", "John Doe", "12/2028", "123");
        final var anItem = Item.createWith("Item name", 2, 2.25f);

        this.order = Order.create(aCustomer, aPaymentData);
        this.order.addItem(anItem);
    }

    @Test
    public void givenValidOrder_whenCreate_thenPersistToDatabase() {
        this.orderGateway.create(this.order);

        final var aModel = this.orderRepository.findById(this.order.getId()).get();

        assertEquals(this.order.getId(), aModel.getId());
        assertEquals(this.order.getCustomer().getName(), aModel.getCustomer().getName());
        assertEquals(this.order.getCustomer().getEmail(), aModel.getCustomer().getEmail());
        assertEquals(this.order.getPaymentData().getCardNumber(), aModel.getPaymentData().getCardNumber());
        assertEquals(this.order.getPaymentData().getCardHolder(), aModel.getPaymentData().getCardHolder());
        assertEquals(this.order.getPaymentData().getExpirationDate(), aModel.getPaymentData().getExpirationDate());
        assertEquals(this.order.getPaymentData().getCvv(), aModel.getPaymentData().getCvv());
        assertEquals(this.order.getItems().get(0).getName(), aModel.getItems().get(0).getName());
        assertEquals(this.order.getItems().get(0).getQuantity(), aModel.getItems().get(0).getQuantity());
        assertEquals(this.order.getItems().get(0).getPrice(), aModel.getItems().get(0).getPrice());
        assertEquals(this.order.getStatus(), aModel.getStatus());
        assertEquals(this.order.getCreatedAt(), aModel.getCreatedAt());
        assertEquals(this.order.getUpdatedAt(), aModel.getUpdatedAt());
    }

    @Test
    public void givenExistentOrderId_whenFindById_thenRetrieveFromDatabase() {
        this.orderGateway.create(this.order);

        final var expectedOrder = this.orderGateway.findById(this.order.getId());

        assertEquals(expectedOrder.getId(), this.order.getId());
        assertEquals(expectedOrder.getCustomer().getName(), this.order.getCustomer().getName());
        assertEquals(expectedOrder.getCustomer().getEmail(), this.order.getCustomer().getEmail());
        assertEquals(expectedOrder.getPaymentData().getCardNumber(), this.order.getPaymentData().getCardNumber());
        assertEquals(expectedOrder.getPaymentData().getCardHolder(), this.order.getPaymentData().getCardHolder());
        assertEquals(expectedOrder.getPaymentData().getExpirationDate(), this.order.getPaymentData().getExpirationDate());
        assertEquals(expectedOrder.getPaymentData().getCvv(), this.order.getPaymentData().getCvv());
        assertEquals(expectedOrder.getItems().get(0).getName(), this.order.getItems().get(0).getName());
        assertEquals(expectedOrder.getItems().get(0).getQuantity(), this.order.getItems().get(0).getQuantity());
        assertEquals(expectedOrder.getItems().get(0).getPrice(), this.order.getItems().get(0).getPrice());
        assertEquals(expectedOrder.getStatus(), this.order.getStatus());
        assertEquals(expectedOrder.getCreatedAt(), this.order.getCreatedAt());
        assertEquals(expectedOrder.getUpdatedAt(), this.order.getUpdatedAt());

    }

    @Test
    public void givenInexistentOrderId_whenFindById_thenReturnNull() {
        final var expectedOrder = this.orderGateway.findById("inexistent-id");

        assertEquals(null, expectedOrder);
    }

    @Test
    public void givenExistentOrders_whenList_thenRetrieveAllFromDatabase() {
        this.orderGateway.create(this.order);

        final var expectedOrders = this.orderGateway.list();

        assertEquals(1, expectedOrders.length);

        assertEquals(expectedOrders[0].getId(), this.order.getId());
        assertEquals(expectedOrders[0].getCustomer().getName(), this.order.getCustomer().getName());
        assertEquals(expectedOrders[0].getCustomer().getEmail(), this.order.getCustomer().getEmail());
        assertEquals(expectedOrders[0].getPaymentData().getCardNumber(), this.order.getPaymentData().getCardNumber());
        assertEquals(expectedOrders[0].getPaymentData().getCardHolder(), this.order.getPaymentData().getCardHolder());
        assertEquals(expectedOrders[0].getPaymentData().getExpirationDate(), this.order.getPaymentData().getExpirationDate());
        assertEquals(expectedOrders[0].getPaymentData().getCvv(), this.order.getPaymentData().getCvv());
        assertEquals(expectedOrders[0].getItems().get(0).getName(), this.order.getItems().get(0).getName());
        assertEquals(expectedOrders[0].getItems().get(0).getQuantity(), this.order.getItems().get(0).getQuantity());
        assertEquals(expectedOrders[0].getItems().get(0).getPrice(), this.order.getItems().get(0).getPrice());
        assertEquals(expectedOrders[0].getStatus(), this.order.getStatus());
        assertEquals(expectedOrders[0].getCreatedAt(), this.order.getCreatedAt());
        assertEquals(expectedOrders[0].getUpdatedAt(), this.order.getUpdatedAt());
    }

}
