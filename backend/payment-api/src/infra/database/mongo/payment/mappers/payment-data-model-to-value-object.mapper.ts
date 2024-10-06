import { PaymentData } from 'src/domain/value-objects/payment-data.value-object';
import { PaymentDataModel } from '../models/payment-data.model';

export class PaymentDataModelToValueObjectMapper {
  public static toValueObject(aModel: PaymentDataModel): PaymentData {
    const aValueObject = PaymentData.with({
      cardNumber: aModel.cardNumber,
      cardHolder: aModel.cardHolder,
      expirationDate: aModel.expirationDate,
      cvv: aModel.cvv,
      createdAt: aModel.createdAt,
      updatedAt: aModel.updatedAt,
    });

    return aValueObject;
  }
}
