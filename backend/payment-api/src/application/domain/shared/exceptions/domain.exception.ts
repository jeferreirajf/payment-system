export class DomainException extends Error {
  protected context: string | undefined;

  constructor(message: string, context?: string) {
    super(message);
    this.name = DomainException.name;
    this.context = context;
  }

  public getContext(): string | undefined {
    return this.context;
  }
}
