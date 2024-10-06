import { Payment } from '../entities/payment.entity';

export interface PaymentGateway {
  create(payment: Payment): Promise<void>;
  update(payment: Payment): Promise<void>;
  findById(id: string): Promise<Payment | null>;
  findByOriginId(originId: string): Promise<Payment | null>;
}
