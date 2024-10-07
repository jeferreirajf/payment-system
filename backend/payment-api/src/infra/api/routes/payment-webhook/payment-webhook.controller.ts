import { Body, Controller, Post } from '@nestjs/common';
import {
  PaymentWebhookProvider,
  PaymentWebhookProviderInputDto,
} from './provider/payment-webhook.provider';
import { PaymentWebhookRequestDto } from './dtos/payment-webhook.dto';

@Controller('/webhook/payment')
export class PaymentWebhookController {
  public constructor(
    private readonly paymentWebhookProvider: PaymentWebhookProvider,
  ) {}

  @Post()
  public async handle(
    @Body() request: PaymentWebhookRequestDto,
  ): Promise<void> {
    const input: PaymentWebhookProviderInputDto = {
      paymentId: request.paymentId,
      status: request.status,
    };

    await this.paymentWebhookProvider.execute(input);

    return;
  }
}
