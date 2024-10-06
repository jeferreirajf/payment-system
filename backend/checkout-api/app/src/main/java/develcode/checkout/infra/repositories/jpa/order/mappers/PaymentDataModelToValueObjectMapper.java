package develcode.checkout.infra.repositories.jpa.order.mappers;

import java.util.function.Function;

import develcode.checkout.domain.valueobjects.PaymentData;
import develcode.checkout.infra.repositories.jpa.order.models.PaymentDataJpaModel;

public class PaymentDataModelToValueObjectMapper implements Function<PaymentDataJpaModel, PaymentData> {

    public static PaymentData map(final PaymentDataJpaModel aModel) {
        return new PaymentDataModelToValueObjectMapper().apply(aModel);
    }

    @Override
    public PaymentData apply(final PaymentDataJpaModel aModel) {
        final var aPaymentData = PaymentData.createWith(
                aModel.getCardNumber(),
                aModel.getCardHolder(),
                aModel.getExpirationDate(),
                 aModel.getCvv()
        );

        return aPaymentData;
    }

}
