package develcode.checkout.domain.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import develcode.checkout.domain.shared.exceptions.BusinessDomainException;
import develcode.checkout.domain.shared.exceptions.ValidationDomainException;
import develcode.checkout.domain.valueobjects.Customer;
import develcode.checkout.domain.valueobjects.Item;
import develcode.checkout.domain.valueobjects.OrderStatus;
import develcode.checkout.domain.valueobjects.PaymentData;

@Testable
public class OrderTest {

    private Customer customer;
    private PaymentData paymentData;

    @BeforeEach
    public void setup() {
        this.customer = Customer.createWith(
                "John Doe",
                "john@doe.com"
        );

        this.paymentData = PaymentData.createWith(
                "John Doe",
                "123456789",
                "123",
                "12/23"
        );
    }

    @Test
    public void givenValidParams_whenCreate_thenShouldCreateAnOrder() {
        final var anOrder = Order.create(this.customer, this.paymentData);

        assertNotNull(anOrder);
        assertNotNull(anOrder.getId());
        assertEquals(this.customer, anOrder.getCustomer());
        assertEquals(this.paymentData, anOrder.getPaymentData());
        assertNotNull(anOrder.getItems());
        assertEquals(0, anOrder.getItems().size());
        assertNotNull(anOrder.getStatus());
        assertEquals(anOrder.getStatus(), OrderStatus.PENDING);
        assertNotNull(anOrder.getCreatedAt());
        assertNotNull(anOrder.getUpdatedAt());
        assertTrue(!anOrder.getCreatedAt().isAfter(anOrder.getUpdatedAt()));
    }

    @Test
    public void givenNullCustomer_whenCreate_thenShouldThrowAnException() {
        final var anException = assertThrows(ValidationDomainException.class, () -> {
            Order.create(null, this.paymentData);
        });

        assertEquals("Order customer should not be null", anException.getMessage());
    }

    @Test
    public void givenNullPaymentData_whenCreate_thenShouldThrowAnException() {
        final var anException = assertThrows(ValidationDomainException.class, () -> {
            Order.create(this.customer, null);
        });

        assertEquals("Order payment data should not be null", anException.getMessage());
    }

    @Test
    public void givenValidParams_whenCreateWith_thenShouldCreateAnOrder() {
        final var anId = UUID.randomUUID().toString();
        final var aCreatedAt = Instant.now();
        final var anUpdatedAt = Instant.now();

        final var anItems = new ArrayList<Item>();

        final var items = new Item[]{
            Item.createWith("Item 1", 2, 2.5f),
            Item.createWith("Item 2", 3, 3.5f),
            Item.createWith("Item 3", 4, 4.5f)
        };

        anItems.addAll(Arrays.asList(items));

        final var anOrder = Order.createWith(
                anId,
                this.customer,
                this.paymentData,
                anItems,
                OrderStatus.APPROVED,
                aCreatedAt,
                anUpdatedAt
        );

        assertNotNull(anOrder);
        assertEquals(anId, anOrder.getId());
        assertEquals(this.customer, anOrder.getCustomer());
        assertEquals(this.paymentData, anOrder.getPaymentData());
        assertNotNull(anOrder.getItems());
        assertEquals(3, anOrder.getItems().size());
        assertEquals(anItems, anOrder.getItems());
        assertEquals(OrderStatus.APPROVED, anOrder.getStatus());
        assertEquals(aCreatedAt, anOrder.getCreatedAt());
        assertEquals(anUpdatedAt, anOrder.getUpdatedAt());
    }

    @Test
    public void givenInvalidId_whenCreateWith_thenShouldThrowAnException() {
        final var invalidIds = new String[]{"", null, "not-a-uuid", "123", "123@3684-4568-789a-Asd8f-846fasdf"};

        for (var invalidId : invalidIds) {
            final var anException = assertThrows(ValidationDomainException.class, () -> {
                Order.createWith(
                        invalidId,
                        this.customer,
                        this.paymentData,
                        new ArrayList<>(),
                        OrderStatus.APPROVED,
                        Instant.now(),
                        Instant.now()
                );
            });

            assertEquals("Order id should be a valid UUID", anException.getMessage());
        }
    }

    @Test
    public void givenNullItems_whenCreateWith_thenShouldThrowAnException() {
        final var anId = UUID.randomUUID().toString();
        final var aCreatedAt = Instant.now();
        final var anUpdatedAt = Instant.now();

        final var anException = assertThrows(ValidationDomainException.class, () -> {
            Order.createWith(
                    anId,
                    this.customer,
                    this.paymentData,
                    null,
                    OrderStatus.APPROVED,
                    aCreatedAt,
                    anUpdatedAt
            );
        });

        assertEquals("Order items should not be null", anException.getMessage());
    }

    @Test
    public void givenNullPaymentData_whenCreateWith_thenShouldThrowAnException() {
        final var anId = UUID.randomUUID().toString();
        final var aCreatedAt = Instant.now();
        final var anUpdatedAt = Instant.now();

        final var anException = assertThrows(ValidationDomainException.class, () -> {
            Order.createWith(
                    anId,
                    this.customer,
                    null,
                    new ArrayList<>(),
                    OrderStatus.APPROVED,
                    aCreatedAt,
                    anUpdatedAt
            );
        });

        assertEquals("Order payment data should not be null", anException.getMessage());
    }

    @Test
    public void givenNullCustomer_whenCreateWith_thenShouldThrowAnException() {
        final var anId = UUID.randomUUID().toString();
        final var aCreatedAt = Instant.now();
        final var anUpdatedAt = Instant.now();

        final var anException = assertThrows(ValidationDomainException.class, () -> {
            Order.createWith(
                    anId,
                    null,
                    this.paymentData,
                    new ArrayList<>(),
                    OrderStatus.APPROVED,
                    aCreatedAt,
                    anUpdatedAt
            );
        });

        assertEquals("Order customer should not be null", anException.getMessage());
    }

    @Test
    public void givenValidParams_whenAddItem_thenShouldAddItemToOrder() {
        final var anOrder = Order.create(this.customer, this.paymentData);

        final var anItem = Item.createWith("Item 1", 2, 2.5f);

        assertNotNull(anOrder.getItems());
        assertEquals(0, anOrder.getItems().size());

        anOrder.addItem(anItem);

        assertNotNull(anOrder.getItems());
        assertEquals(1, anOrder.getItems().size());
        assertEquals(anItem, anOrder.getItems().get(0));
    }

    @Test
    public void givenValidOrder_whenCalculateTotal_thenShouldCalculateTotal() {
        final var anOrder = Order.create(this.customer, this.paymentData);

        final var items = new Item[]{
            Item.createWith("Item 1", 2, 2.5f),
            Item.createWith("Item 2", 3, 3.5f),
            Item.createWith("Item 3", 4, 4.5f)
        };

        for (var item : items) {
            anOrder.addItem(item);
        }

        final var expectedTotal = 2 * 2.5f + 3 * 3.5f + 4 * 4.5f;

        assertEquals(expectedTotal, anOrder.getTotal());
    }

    @Test
    public void givenValidOrder_whenApprove_thenShouldApproveOrder() {
        final var anOrder = Order.create(this.customer, this.paymentData);

        assertEquals(OrderStatus.PENDING, anOrder.getStatus());

        anOrder.approve();

        assertEquals(OrderStatus.APPROVED, anOrder.getStatus());
    }

    @Test
    public void givenInvalidOrderStatus_whenApprove_thenShouldThrowAnException() {
        final var id = UUID.randomUUID().toString();
        final var items = new ArrayList<Item>();
        final var status = OrderStatus.APPROVED;
        final var createdAt = Instant.now();
        final var updatedAt = Instant.now();

        final var anOrder = Order.createWith(
                id,
                this.customer,
                this.paymentData,
                items,
                status,
                createdAt,
                updatedAt
        );

        final var anException = assertThrows(BusinessDomainException.class, () -> {
            anOrder.approve();
        });

        assertEquals("Order should be in PENDING status to be approved. Current status: APPROVED", anException.getMessage());
    }

    @Test
    public void givenValidOrder_whenReject_thenShouldRejectOrder() {
        final var anOrder = Order.create(this.customer, this.paymentData);

        assertEquals(OrderStatus.PENDING, anOrder.getStatus());

        anOrder.reject();

        assertEquals(OrderStatus.REJECTED, anOrder.getStatus());
    }

    @Test
    public void givenInvalidOrderStatus_whenReject_thenShouldThrowAnException() {
        final var id = UUID.randomUUID().toString();
        final var items = new ArrayList<Item>();
        final var status = OrderStatus.REJECTED;
        final var createdAt = Instant.now();
        final var updatedAt = Instant.now();

        final var anOrder = Order.createWith(
                id,
                this.customer,
                this.paymentData,
                items,
                status,
                createdAt,
                updatedAt
        );

        final var anException = assertThrows(BusinessDomainException.class, () -> {
            anOrder.reject();
        });

        assertEquals("Order should be in PENDING status to be rejected. Current status: REJECTED", anException.getMessage());
    }
}
