import { EventHandler } from 'src/handlers/event-handler';
import { MessagingGateway } from '../messaging';
import { Event } from 'src/domain/events/event';
import { Injectable } from '@nestjs/common';

@Injectable()
export class KafkaMessaging extends MessagingGateway {
  async publish(topic: string, message: Event): Promise<void> {
    console.log(`Publishing to ${topic}: ${message}`);
  }

  async subscribe(topic: string, handler: EventHandler): Promise<void> {
    console.log(`Subscribing to ${topic}`);
  }
}

export const KafkaMessagingProvider = {
  provide: MessagingGateway,
  useClass: KafkaMessaging,
};
