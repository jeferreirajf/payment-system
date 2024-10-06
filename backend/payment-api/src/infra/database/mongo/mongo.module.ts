import { Module } from '@nestjs/common';
import { ConfigService } from '@nestjs/config';
import { MongooseModule } from '@nestjs/mongoose';
import {
  PaymentDataModel,
  PaymentDataSchema,
} from './payment/models/payment-data.model';
import { PaymentModel, PaymentSchema } from './payment/models/payment.model';
import { PaymentRepository } from './payment/payment.repository';

@Module({
  imports: [
    MongooseModule.forRootAsync({
      inject: [ConfigService],
      useFactory: async (configService: ConfigService) => ({
        uri: configService.get('MONGO_DATABASE_URL'),
      }),
    }),
    MongooseModule.forFeature([
      { name: PaymentDataModel.name, schema: PaymentDataSchema },
      { name: PaymentModel.name, schema: PaymentSchema },
    ]),
  ],
  exports: [MongooseModule, PaymentRepository],
  providers: [PaymentRepository],
})
export class MongoModule {}
