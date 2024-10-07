package develcode.checkout.infra.repositories.jpa.order.models;

import develcode.checkout.domain.valueobjects.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Customers")
public class CustomerJpaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = OrderJpaModel.class)
    @JoinColumn(name = "orderId")
    private OrderJpaModel order;

    public CustomerJpaModel() {
    }

    public static CustomerJpaModel from(final Customer customer, final OrderJpaModel order) {
        CustomerJpaModel aModel = new CustomerJpaModel();
        aModel.setName(customer.getName());
        aModel.setEmail(customer.getEmail());
        aModel.setOrder(order);
        return aModel;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the order
     */
    public OrderJpaModel getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(OrderJpaModel order) {
        this.order = order;
    }

}
