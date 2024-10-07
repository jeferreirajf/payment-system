import { Event } from 'src/domain/events/event';
import { EventHandler } from 'src/handlers/event-handler';

export abstract class MessagingGateway {
  abstract publish(topic: string, event: Event): Promise<void>;
  abstract subscribe(topic: string, handler: EventHandler): Promise<void>;
}
