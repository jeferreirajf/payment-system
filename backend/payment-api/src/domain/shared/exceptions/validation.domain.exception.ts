import { DomainException } from './domain.exception';

export class ValidationDomainException extends DomainException {
  constructor(message: string, context?: string) {
    super(message, context);
    this.name = ValidationDomainException.name;
    this.context = context;
  }
}
