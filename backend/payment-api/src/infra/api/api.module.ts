import { Module } from '@nestjs/common';
import { PaymentWebhookProvider } from './routes/payment-webhook/provider/payment-webhook.provider';
import { PaymentWebhookController } from './routes/payment-webhook/payment-webhook.controller';
import { DatabaseModule } from '../database/database.module';
import { MessagingModule } from '../messaging/messaging.module';

@Module({
  imports: [DatabaseModule, MessagingModule],
  controllers: [PaymentWebhookController],
  providers: [PaymentWebhookProvider],
})
export class ApiModule {}
