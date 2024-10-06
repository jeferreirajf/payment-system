import { Prop, Schema, SchemaFactory } from '@nestjs/mongoose';

@Schema({ collection: 'paymentData' })
export class PaymentDataModel {
  @Prop({ required: true })
  cardNumber: string;
  @Prop({ required: true })
  cardHolder: string;
  @Prop({ required: true })
  expirationDate: string;
  @Prop({ required: true })
  cvv: string;
  @Prop({ required: true })
  createdAt: Date;
  @Prop({ required: true })
  updatedAt: Date;
}

export const PaymentDataSchema = SchemaFactory.createForClass(PaymentDataModel);
