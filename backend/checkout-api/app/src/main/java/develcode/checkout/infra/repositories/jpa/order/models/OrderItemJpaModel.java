package develcode.checkout.infra.repositories.jpa.order.models;

import develcode.checkout.domain.valueobjects.Item;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "OrderItem")
public class OrderItemJpaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private Float price;

    @ManyToOne(
            targetEntity = OrderJpaModel.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "orderId", referencedColumnName = "id")
    private OrderJpaModel order;

    public OrderItemJpaModel() {
    }

    public static OrderItemJpaModel from(final Item item) {
        OrderItemJpaModel aModel = new OrderItemJpaModel();
        aModel.setName(item.getName());
        aModel.setQuantity(item.getQuantity());
        aModel.setPrice(item.getPrice());
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
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public Float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Float price) {
        this.price = price;
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
