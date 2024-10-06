export class UUIDGenerator {
  public static generate(): string {
    const uuid = crypto.randomUUID().toString();
    return uuid;
  }
}
