export class UsecaseException extends Error {
  protected context: string;
  constructor(message: string, context?: string) {
    super(message);
    this.name = UsecaseException.name;
    this.context = context;
  }

  public getContext(): string {
    return this.context;
  }
}
