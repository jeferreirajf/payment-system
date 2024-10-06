import { Payment } from 'src/domain/entities/payment.entity';

export interface ExternalPaymentGateway {
  pay(payment: Payment): Promise<void>;
}
