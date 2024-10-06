import { Prop, Schema, SchemaFactory } from '@nestjs/mongoose';
import { PaymentDataModel } from './payment-data.model';
import { PaymentStatus } from 'src/application/domain/entities/payment.entity';

@Schema({ collection: 'payments' })
export class PaymentModel {
  @Prop({ required: true })
  id: string;
  @Prop({ required: true })
  originId: string;
  @Prop({ required: true })
  amount: number;
  @Prop({ required: true, enum: PaymentStatus })
  status: PaymentStatus;
  @Prop({ required: true })
  paymentData: PaymentDataModel;
  @Prop({ required: false })
  paymentResponseDate: Date | undefined;
  @Prop({ required: true })
  createdAt: Date;
  @Prop({ required: true })
  updatedAt: Date;
}

export const PaymentSchema = SchemaFactory.createForClass(PaymentModel);
