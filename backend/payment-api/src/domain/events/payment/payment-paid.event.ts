import { PaymentStatus } from 'src/domain/entities/payment.entity';
import { Event } from '../event';

export type PaymentPaidPayload = {
  id: string;
  paymentId: string;
  status: PaymentStatus.APPROVED;
};

export class PaymentPaidEvent extends Event {
  private constructor() {
    super('PaymentPaidEvent');
  }

  public static create(): PaymentPaidEvent {
    return new PaymentPaidEvent();
  }
}
