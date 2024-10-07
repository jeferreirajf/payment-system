import { UUIDGenerator } from 'src/utils/uuid-generator';

export abstract class Event {
  private id: string;
  private name: string;
  private payload: any;

  protected constructor(name: string) {
    this.id = UUIDGenerator.generate();
    this.name = name;
    this.payload = null;
  }

  public getName(): string {
    return this.name;
  }

  public getPayload(): any {
    return this.payload;
  }

  public setPayload(payload: any): void {
    this.payload = payload;
  }
}
