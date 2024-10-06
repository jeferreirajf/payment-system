import { UsecaseException } from './usecase.exception';

export class NotFoundUsecaseException extends UsecaseException {
  constructor(message: string, context?: string) {
    super(message, context);
    this.name = NotFoundUsecaseException.name;
  }
}
