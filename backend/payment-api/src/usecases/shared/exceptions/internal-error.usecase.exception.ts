import { UsecaseException } from './usecase.exception';

export class InternalErrorUsecaseException extends UsecaseException {
  constructor(message: string, context?: string) {
    super(message, context);
    this.name = InternalErrorUsecaseException.name;
  }
}
