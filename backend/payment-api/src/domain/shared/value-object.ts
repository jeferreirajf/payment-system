export abstract class ValueObject {
  protected constructor(
    private createdAt: Date,
    private updatedAt: Date,
  ) {}

  public getCreatedAt(): Date {
    return this.createdAt;
  }

  public getUpdatedAt(): Date {
    return this.updatedAt;
  }

  protected hasChanged(): void {
    this.updatedAt = new Date();
  }

  protected abstract validate(): void;
}
