import { Payment } from 'src/application/domain/entities/payment.entity';
import { PaymentModel } from '../models/payment.model';
import { PaymentDataValueObjectToModelMapper } from './payment-data-value-object-to-model.mapper';

export class PaymentEntityToModelMapper {
  public static toModel(anEntity: Payment): PaymentModel {
    const paymentDataModel = PaymentDataValueObjectToModelMapper.toModel(
      anEntity.getPaymentData(),
    );

    const aModel = new PaymentModel();

    aModel.originId = anEntity.getOriginId();
    aModel.amount = anEntity.getAmount();
    aModel.status = anEntity.getStatus();
    aModel.paymentData = paymentDataModel;
    aModel.paymentResponseDate = anEntity.getPaymentResponseDate();
    aModel.createdAt = anEntity.getCreatedAt();
    aModel.updatedAt = anEntity.getUpdatedAt();

    return aModel;
  }
}
