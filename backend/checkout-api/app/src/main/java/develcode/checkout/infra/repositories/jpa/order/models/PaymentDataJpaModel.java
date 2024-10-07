package develcode.checkout.infra.repositories.jpa.order.models;

import develcode.checkout.domain.valueobjects.PaymentData;
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
@Table(name = "PaymentData")
public class PaymentDataJpaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "cardNumber", nullable = false)
    private String cardNumber;

    @Column(name = "cardHolder", nullable = false)
    private String cardHolder;

    @Column(name = "expirationDate", nullable = false)
    private String expirationDate;

    @Column(name = "cvv", nullable = false)
    private String cvv;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = OrderJpaModel.class)
    @JoinColumn(name = "orderId")
    private OrderJpaModel order;

    public PaymentDataJpaModel() {
    }

    public static PaymentDataJpaModel from(final PaymentData paymentData, final OrderJpaModel order) {
        PaymentDataJpaModel aModel = new PaymentDataJpaModel();
        aModel.setCardNumber(paymentData.getCardNumber());
        aModel.setCardHolder(paymentData.getCardHolder());
        aModel.setExpirationDate(paymentData.getExpirationDate());
        aModel.setCvv(paymentData.getCvv());
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
     * @return the cardNumber
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * @param cardNumber the cardNumber to set
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * @return the cardHolder
     */
    public String getCardHolder() {
        return cardHolder;
    }

    /**
     * @param cardHolder the cardHolder to set
     */
    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    /**
     * @return the expirationDate
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * @param expirationDate the expirationDate to set
     */
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * @return the cvv
     */
    public String getCvv() {
        return cvv;
    }

    /**
     * @param cvv the cvv to set
     */
    public void setCvv(String cvv) {
        this.cvv = cvv;
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
