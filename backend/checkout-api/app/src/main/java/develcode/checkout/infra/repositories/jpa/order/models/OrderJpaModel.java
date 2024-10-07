package develcode.checkout.infra.repositories.jpa.order.models;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import develcode.checkout.domain.entities.Order;
import develcode.checkout.domain.valueobjects.OrderStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name = "order")
@Table(name = "Orders")
public class OrderJpaModel {

    @Id
    @Column(name = "id", nullable = false)
    private String id;
    @OneToOne(
            targetEntity = CustomerJpaModel.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "order"
    )
    private CustomerJpaModel customer;
    @OneToOne(
            targetEntity = PaymentDataJpaModel.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "order"
    )
    private PaymentDataJpaModel paymentData;
    @OneToMany(
            targetEntity = OrderItemJpaModel.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "orderId")
    private List<OrderItemJpaModel> items;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;
    @Column(name = "createdAt", nullable = false)
    private Instant createdAt;
    @Column(name = "updatedAt", nullable = false)
    private Instant updatedAt;

    public OrderJpaModel() {
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    public static OrderJpaModel from(final Order order) {
        OrderJpaModel aModel = new OrderJpaModel();

        aModel.setId(order.getId());
        aModel.setStatus(order.getStatus());
        aModel.setCreatedAt(order.getCreatedAt());
        aModel.setUpdatedAt(order.getUpdatedAt());
        aModel.setItems(order.getItems().stream().map(OrderItemJpaModel::from).collect(Collectors.toList()));

        aModel.setCustomer(CustomerJpaModel.from(order.getCustomer(), aModel));
        aModel.setPaymentData(PaymentDataJpaModel.from(order.getPaymentData(), aModel));

        return aModel;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the customer
     */
    public CustomerJpaModel getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(CustomerJpaModel customer) {
        this.customer = customer;
    }

    /**
     * @return the paymentData
     */
    public PaymentDataJpaModel getPaymentData() {
        return paymentData;
    }

    /**
     * @param paymentData the paymentData to set
     */
    public void setPaymentData(PaymentDataJpaModel paymentData) {
        this.paymentData = paymentData;
    }

    /**
     * @return the items
     */
    public List<OrderItemJpaModel> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<OrderItemJpaModel> items) {
        this.items = items;
    }

    /**
     * @return the status
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    /**
     * @return the createdAt
     */
    public Instant getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return the updatedAt
     */
    public Instant getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt the updatedAt to set
     */
    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

}
