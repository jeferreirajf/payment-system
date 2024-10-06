import { PaymentDataValidatorFactory } from '../factories/payment-data.validator.factory';
import { ValueObject } from '../shared/value-object';

export type PaymentDataCreateDto = {
  cardNumber: string;
  cardHolder: string;
  expirationDate: string;
  cvv: string;
};

export type PaymentDataWithDto = {
  cardNumber: string;
  cardHolder: string;
  expirationDate: string;
  cvv: string;
  createdAt: Date;
  updatedAt: Date;
};

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

  public static create({
    cardNumber,
    cardHolder,
    expirationDate,
    cvv,
  }: PaymentDataCreateDto): PaymentData {
    return new PaymentData(
      cardNumber,
      cardHolder,
      expirationDate,
      cvv,
      new Date(),
      new Date(),
    );
  }

  public static with({
    cardNumber,
    cardHolder,
    expirationDate,
    cvv,
    createdAt,
    updatedAt,
  }: PaymentDataWithDto): PaymentData {
    return new PaymentData(
      cardNumber,
      cardHolder,
      expirationDate,
      cvv,
      createdAt,
      updatedAt,
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
