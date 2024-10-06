export abstract class Entity {
  protected constructor(
    protected readonly id: string,
    private createdAt: Date,
    private updatedAt: Date,
  ) {}

  public getCreatedAt(): Date {
    return this.createdAt;
  }

  public getId(): string {
    return this.id;
  }

  public getUpdatedAt(): Date {
    return this.updatedAt;
  }

  protected hasChanged(): void {
    this.updatedAt = new Date();
  }

  protected abstract validate(): void;
}
