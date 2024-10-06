import { PaymentData } from 'src/domain/value-objects/payment-data.value-object';
import { PaymentDataModel } from '../models/payment-data.model';

export class PaymentDataValueObjectToModelMapper {
  public static toModel(anEntity: PaymentData): PaymentDataModel {
    const aModel = new PaymentDataModel();

    aModel.cardNumber = anEntity.getCardNumber();
    aModel.cardHolder = anEntity.getCardHolder();
    aModel.expirationDate = anEntity.getExpirationDate();
    aModel.cvv = anEntity.getCvv();
    aModel.createdAt = anEntity.getCreatedAt();
    aModel.updatedAt = anEntity.getUpdatedAt();

    return aModel;
  }
}
