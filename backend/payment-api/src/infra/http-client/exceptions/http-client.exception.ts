import { InfraException } from 'src/infra/shared/exceptions/infra.exception';

export class HttpClientException extends InfraException {
  constructor(message: string, context?: string) {
    super(message);
    this.name = 'FetchClientException';
    this.context = context;
  }
}
