import { UUIDGenerator } from 'src/utils/uuid-generator';
import { Entity } from '../shared/entity';
import { PaymentData } from '../value-objects/payment-data.value-object';
import { DomainException } from '../shared/exceptions/domain.exception';

export enum PaymentStatus {
  PENDING = 'PENDING',
  APPROVED = 'APPROVED',
  REJECTED = 'REJECTED',
}

export type PaymentCreateDto = {
  originId: string;
  amount: number;
  paymentData: PaymentData;
};

export type PaymentWithDto = {
  id: string;
  originId: string;
  amount: number;
  status: PaymentStatus;
  paymentData: PaymentData;
  paymentResponseDate: Date | undefined;
  createdAt: Date;
  updatedAt: Date;
};

export class Payment extends Entity {
  constructor(
    id: string,
    private readonly originId: string,
    private readonly amount: number,
    private status: PaymentStatus,
    private readonly paymentData: PaymentData,
    private paymentResponseDate: Date | undefined,
    createdAt: Date,
    updatedAt: Date,
  ) {
    super(id, createdAt, updatedAt);
    this.validate();
  }

  public static create({
    originId,
    amount,
    paymentData,
  }: PaymentCreateDto): Payment {
    const id = UUIDGenerator.generate();
    const createdAt = new Date();
    const updatedAt = new Date();

    const status = PaymentStatus.PENDING;

    return new Payment(
      id,
      originId,
      amount,
      status,
      paymentData,
      undefined,
      createdAt,
      updatedAt,
    );
  }

  public static with(input: PaymentWithDto): Payment {
    return new Payment(
      input.id,
      input.originId,
      input.amount,
      input.status,
      input.paymentData,
      input.paymentResponseDate,
      input.createdAt,
      input.updatedAt,
    );
  }

  protected validate(): void {}

  public getOriginId(): string {
    return this.originId;
  }

  public getAmount(): number {
    return this.amount;
  }

  public getStatus(): PaymentStatus {
    return this.status;
  }

  public getPaymentData(): PaymentData {
    return this.paymentData;
  }

  public getPaymentResponseDate(): Date | undefined {
    return this.paymentResponseDate;
  }

  public approve(): Payment {
    if (this.status !== PaymentStatus.PENDING) {
      throw new DomainException(
        `Payment with id ${this.id} status is ${this.status} and cannot be approved`,
        Payment.name,
      );
    }

    this.paymentResponseDate = new Date();

    this.status = PaymentStatus.APPROVED;
    this.hasChanged();

    return this;
  }

  public reject(): Payment {
    if (this.status !== PaymentStatus.PENDING) {
      throw new DomainException(
        `Payment with id ${this.id} status is ${this.status} and cannot be rejected`,
        Payment.name,
      );
    }

    this.paymentResponseDate = new Date();

    this.status = PaymentStatus.REJECTED;
    this.hasChanged();

    return this;
  }

  public setPaymentResponseDate(date: Date): Payment {
    this.paymentResponseDate = date;
    this.hasChanged();

    return this;
  }

  public isPending(): boolean {
    return this.status === PaymentStatus.PENDING;
  }
}
