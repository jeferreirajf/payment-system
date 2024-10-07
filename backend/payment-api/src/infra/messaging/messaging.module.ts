import { Module } from '@nestjs/common';
import { DatabaseModule } from '../database/database.module';
import { KafkaBootstrap } from './kafka/kafka.bootstrap';
import {
  KafkaMessaging,
  KafkaMessagingProvider,
} from './kafka/kafka.messaging';

@Module({
  imports: [DatabaseModule],
  exports: [KafkaMessagingProvider],
  providers: [KafkaMessagingProvider, KafkaBootstrap, KafkaMessaging],
})
export class MessagingModule {}
