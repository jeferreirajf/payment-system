import { Event } from 'src/domain/events/event';
import { EventHandler } from 'src/handlers/event-handler';

export interface MessagingGateway {
  publish(topic: string, event: Event): Promise<void>;
  subscribe(topic: string, handler: EventHandler): Promise<void>;
}
