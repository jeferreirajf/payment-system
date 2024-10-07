package develcode.checkout.domain.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import develcode.checkout.domain.shared.aggregates.AggregateRoot;
import develcode.checkout.domain.shared.entities.Entity;
import develcode.checkout.domain.shared.exceptions.BusinessDomainException;
import develcode.checkout.domain.validators.OrderValidator;
import develcode.checkout.domain.valueobjects.Customer;
import develcode.checkout.domain.valueobjects.Item;
import develcode.checkout.domain.valueobjects.OrderStatus;
import develcode.checkout.domain.valueobjects.PaymentData;

public class Order extends Entity implements AggregateRoot {

    private Customer customer;
    private PaymentData paymentData;
    private List<Item> items;
    private OrderStatus status;

    private Order(String id, Customer customer, PaymentData paymentData, List<Item> items, OrderStatus status,
            Instant createdAt, Instant updatedAt) {
        super(id, createdAt, updatedAt);
        this.customer = customer;
        this.paymentData = paymentData;
        this.items = items;
        this.status = status;

        this.validate();
    }

    public static Order create(final Customer customer, final PaymentData paymentData) {
        final var id = UUID.randomUUID().toString();
        final var createdAt = Instant.now();
        final var updatedAt = Instant.now();

        final var status = OrderStatus.PENDING;
        final var items = new ArrayList<Item>();

        return new Order(id, customer, paymentData, items, status, createdAt, updatedAt);
    }

    public static Order createWith(
            final String id,
            final Customer customer,
            final PaymentData paymentData,
            final List<Item> items,
            final OrderStatus status,
            final Instant createdAt,
            final Instant updatedAt) {
        return new Order(id, customer, paymentData, items, status, createdAt, updatedAt);
    }

    public Order addItem(final Item item) {
        this.items.add(item);
        return this;
    }

    public Order approve() {
        if (this.status != OrderStatus.PENDING) {
            throw new BusinessDomainException(
                    "Order should be in PENDING status to be approved. Current status: " + this.status);
        }

        this.status = OrderStatus.APPROVED;
        this.hashChanged();
        return this;
    }

    public Order reject() {
        if (this.status != OrderStatus.PENDING) {
            throw new BusinessDomainException(
                    "Order should be in PENDING status to be rejected. Current status: " + this.status);
        }

        this.status = OrderStatus.REJECTED;
        this.hashChanged();
        return this;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public PaymentData getPaymentData() {
        return this.paymentData;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public float getTotal() {
        final var totalValues = this.items.stream().map(Item::getTotal);
        final var total = totalValues.reduce(0.0f, Float::sum);
        return total;
    }

    @Override
    protected void validate() {
        OrderValidator.create().validate(this);
    }

    @Override
    public String toString() {
        return "Order [customer=" + customer + ", paymentData=" + paymentData + ", items=" + items + ", status="
                + status + "]";
    }

}
