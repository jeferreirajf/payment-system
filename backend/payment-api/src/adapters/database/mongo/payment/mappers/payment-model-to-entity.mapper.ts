import { Payment } from 'src/application/domain/entities/payment.entity';
import { PaymentModel } from '../models/payment.model';
import { PaymentDataModelToValueObjectMapper } from './payment-data-model-to-value-object.mapper';

export class PaymentModelToEntityMapper {
  public static toEntity(aModel: PaymentModel): Payment {
    const paymentDataValueObject =
      PaymentDataModelToValueObjectMapper.toValueObject(aModel.paymentData);

    const anEntity = Payment.with({
      id: aModel.id,
      originId: aModel.originId,
      amount: aModel.amount,
      status: aModel.status,
      paymentData: paymentDataValueObject,
      paymentResponseDate: aModel.paymentResponseDate,
      createdAt: aModel.createdAt,
      updatedAt: aModel.updatedAt,
    });

    return anEntity;
  }
}
