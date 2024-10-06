export interface Validator<Input> {
  validate(input: Input): void;
}
