import { EventHandler } from 'src/handlers/event-handler';
import { MessagingGateway } from '../messaging';
import { Event } from 'src/domain/events/event';
import { Injectable } from '@nestjs/common';
import { Consumer, Kafka, Producer } from 'kafkajs';

@Injectable()
export class KafkaMessaging extends MessagingGateway {
  private KAFKA_URL = process.env.KAFKA_URL || 'localhost:9092';

  private producer: Producer;
  private consumer: Consumer;

  private handlers: Map<string, EventHandler>;

  public constructor() {
    super();

    const kafka = new Kafka({
      clientId: 'payment-api',
      brokers: [this.KAFKA_URL],
    });

    this.producer = kafka.producer();

    this.consumer = kafka.consumer({
      groupId: 'develcode-payment-api',
    });

    this.handlers = new Map();
  }

  // @Override
  async publish(topic: string, message: Event): Promise<void> {
    await this.producer.connect();
    await this.producer.send({
      topic,
      messages: [{ value: JSON.stringify(message) }],
    });
    await this.producer.disconnect();
  }

  // @Override
  async subscribe(topic: string, handler: EventHandler): Promise<void> {
    this.handlers.set(topic, handler);
  }

  async start(): Promise<void> {
    await this.consumer.connect();
    await this.consumer.subscribe({ topic: 'payment', fromBeginning: true });

    await this.consumer.run({
      eachMessage: async ({ message }) => {
        const parsedMessage = JSON.parse(message.value.toString());

        console.log(parsedMessage);

        const eventName = parsedMessage?.name;

        if (!eventName) {
          return;
        }

        const payload = parsedMessage?.payload;

        const aHandler = await this.handlers.get(eventName);

        if (!aHandler) {
          return;
        }

        await aHandler.handle(payload);
      },
    });
  }
}

export const KafkaMessagingProvider = {
  provide: MessagingGateway,
  useClass: KafkaMessaging,
};
