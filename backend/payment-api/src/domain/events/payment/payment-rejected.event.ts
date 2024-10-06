import { PaymentStatus } from 'src/domain/entities/payment.entity';
import { Event } from '../event';

export type PaymentRejectedPayload = {
  id: string;
  paymentId: string;
  status: PaymentStatus.REJECTED;
};

export class PaymentRejectedEvent extends Event {
  private constructor() {
    super('PaymentRejectedEvent');
  }

  public static create(): PaymentRejectedEvent {
    return new PaymentRejectedEvent();
  }
}
