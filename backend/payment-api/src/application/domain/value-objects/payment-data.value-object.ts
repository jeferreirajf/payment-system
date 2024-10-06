import { PaymentDataValidatorFactory } from '../factories/payment-data.validator.factory';
import { ValueObject } from '../shared/value-object';
import { PaymentDataZodValidator } from '../validators/payment-data.zod.validator';

export class PaymentData extends ValueObject {
  private constructor(
    private readonly cardNumber: string,
    private readonly cardHolder: string,
    private readonly expirationDate: string,
    private readonly cvv: string,
    createdAt: Date,
    updatedAt: Date,
  ) {
    super(createdAt, updatedAt);
    this.validate();
  }

  public static createWith(
    cardNumber: string,
    cardHolder: string,
    expirationDate: string,
    cvv: string,
  ): PaymentData {
    return new PaymentData(
      cardNumber,
      cardHolder,
      expirationDate,
      cvv,
      new Date(),
      new Date(),
    );
  }

  protected validate(): void {
    PaymentDataValidatorFactory.create().validate(this);
  }

  public getCardNumber(): string {
    return this.cardNumber;
  }

  public getCardHolder(): string {
    return this.cardHolder;
  }

  public getExpirationDate(): string {
    return this.expirationDate;
  }

  public getCvv(): string {
    return this.cvv;
  }
}
