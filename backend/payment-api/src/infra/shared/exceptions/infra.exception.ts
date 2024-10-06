export class InfraException extends Error {
  protected context?: string;

  constructor(message: string, context?: string) {
    super(message);
    this.name = InfraException.name;
    this.context = context;
  }
}
