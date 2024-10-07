import { Injectable } from '@nestjs/common';
import { KafkaMessaging } from './kafka.messaging';
import { OrderCreatedEventHandler } from 'src/handlers/order/order-created.event-handler';
import { HttpClient } from 'src/infra/http-client/http-client.gateway';
import { GoPayExternalPayment } from 'src/infra/payment-gateway/go-pay/go-pay.external-payment';
import { PaymentRepository } from 'src/infra/database/mongo/payment/payment.repository';
import { EventHandler } from 'src/handlers/event-handler';
import { OrderCreatedEvent } from 'src/domain/events/order/order-created.event';
import { FetchHttpClientAdapter } from 'src/infra/http-client/fetch/fetch.http-client.adapter';

@Injectable()
export class KafkaBootstrap {
  private handlers: Map<string, EventHandler>;

  constructor(
    private readonly kafkaMessaging: KafkaMessaging,
    paymentGateway: PaymentRepository,
  ) {
    const httpClient = FetchHttpClientAdapter.build({
      baseUrl: process.env.GO_PAYMENT_URL || 'http://localhost:6001',
    });

    const externalPaymentGateway = GoPayExternalPayment.create({
      httpClient,
    });

    const orderEventHandler = OrderCreatedEventHandler.create({
      externalPaymentGateway,
      paymentGateway,
    });

    this.handlers = new Map();

    this.handlers.set(OrderCreatedEvent.create().getName(), orderEventHandler);
  }

  async onModuleInit(): Promise<void> {
    this.handlers.forEach((handler, event) => {
      this.kafkaMessaging.subscribe(event, handler);
    });

    await this.kafkaMessaging.start();
  }
}
