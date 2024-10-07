import { Payment } from 'src/domain/entities/payment.entity';
import {
  OrderCreatedEvent,
  OrderCreatedPayload,
} from 'src/domain/events/order/order-created.event';
import { PaymentGateway } from 'src/domain/gateways/payment.gateway';
import { PaymentData } from 'src/domain/value-objects/payment-data.value-object';
import { Options } from 'src/infra/http-client/http-client.dto';
import { HttpClient } from 'src/infra/http-client/http-client.gateway';
import { InfraException } from 'src/infra/shared/exceptions/infra.exception';
import { EventHandler } from '../event-handler';
import { ExternalPaymentGateway } from 'src/infra/payment-gateway/external-payment.gateway';

export type OrderCreatedEventHandlerCreateDto = {
  paymentGateway: PaymentGateway;
  externalPaymentGateway: ExternalPaymentGateway;
};

export class OrderCreatedEventHandler implements EventHandler {
  private constructor(
    private readonly paymentGateway: PaymentGateway,
    private readonly externalPaymentGateway: ExternalPaymentGateway,
  ) {}

  public static create({
    paymentGateway,
    externalPaymentGateway,
  }: OrderCreatedEventHandlerCreateDto): OrderCreatedEventHandler {
    if (!paymentGateway || !externalPaymentGateway) {
      throw new InfraException(
        `Missing dependency while creating ${OrderCreatedEventHandler.name}`,
      );
    }

    return new OrderCreatedEventHandler(paymentGateway, externalPaymentGateway);
  }

  async handle(event: any): Promise<void> {
    console.log('OrderCreatedEventHandler', event);

    const aPaymentData = PaymentData.create({
      cardHolder: event.paymentData.cardHolder,
      cardNumber: event.paymentData.cardNumber,
      expirationDate: event.paymentData.expirationDate,
      cvv: event.paymentData.cvv,
    });

    const amount = event.items.reduce(
      (acc, item) => acc + item.price * item.quantity,
      0,
    );

    const aPaymentToProcess = Payment.create({
      amount,
      originId: event.id,
      paymentData: aPaymentData,
    });

    console.log('Payment to process', aPaymentToProcess);

    await this.paymentGateway.create(aPaymentToProcess);

    await this.externalPaymentGateway.pay(aPaymentToProcess);
  }
}
