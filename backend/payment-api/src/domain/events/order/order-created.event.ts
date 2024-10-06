import { Event } from '../event';

export type OrderCreatedPayload = {
  id: string;
  paymentData: {
    cardNumber: string;
    cardHolder: string;
    expirationDate: string;
    cvv: string;
  };
  items: {
    name: string;
    quantity: number;
    price: number;
  }[];
};

export class OrderCreatedEvent extends Event {
  private constructor() {
    super('OrderCreatedEvent');
  }

  public static create(): OrderCreatedEvent {
    return new OrderCreatedEvent();
  }
}
