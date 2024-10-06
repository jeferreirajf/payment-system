import { Validator } from '../shared/validator';
import { PaymentDataZodValidator } from '../validators/payment-data.zod.validator';
import { PaymentData } from '../value-objects/payment-data.value-object';

export class PaymentDataValidatorFactory {
  public static create(): Validator<PaymentData> {
    return PaymentDataZodValidator.create();
  }
}
