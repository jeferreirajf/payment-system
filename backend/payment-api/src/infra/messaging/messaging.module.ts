import { Module } from '@nestjs/common';
import { KafkaMessagingProvider } from './kafka/kafka.messaging';

@Module({
  exports: [KafkaMessagingProvider],
  providers: [KafkaMessagingProvider],
})
export class MessagingModule {}
