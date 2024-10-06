import { HttpClient } from 'src/infra/http-client/http-client.gateway';
import { ExternalPaymentGateway } from '../external-payment.gateway';
import { InfraException } from 'src/infra/shared/exceptions/infra.exception';
import { Payment } from 'src/domain/entities/payment.entity';
import { Options } from 'src/infra/http-client/http-client.dto';

export type GoPayPaymentBuildDto = {
  httpClient: HttpClient;
};

export class GoPayExternalPayment implements ExternalPaymentGateway {
  private constructor(private readonly httpClient: HttpClient) {}

  public static create({
    httpClient,
  }: GoPayPaymentBuildDto): GoPayExternalPayment {
    if (!httpClient) {
      throw new InfraException(
        `Missing dependency while creating ${GoPayExternalPayment.name}`,
        GoPayExternalPayment.name,
      );
    }

    return new GoPayExternalPayment(httpClient);
  }

  async pay(aPayment: Payment): Promise<void> {
    const gatewayEndpoint = `/orders/place`;

    const headers = {
      'Content-Type': 'application/json',
    };

    const options: Options = {
      headers,
    };

    const data = {
      paymentId: aPayment.getId(),
      amount: aPayment.getAmount(),
      callback:
        process.env.PAYMENT_CALLBACK_URL ||
        'http://localhost:3030/webhook/payment',
    };

    await this.httpClient.post<null>({
      endpoint: gatewayEndpoint,
      options,
      data,
    });
  }
}
