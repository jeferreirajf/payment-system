import { Event } from 'src/domain/events/event';

export interface EventHandler {
  handle(event: Event): Promise<void>;
}
