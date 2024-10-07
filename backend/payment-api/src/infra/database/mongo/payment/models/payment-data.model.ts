import { Prop, Schema, SchemaFactory } from '@nestjs/mongoose';

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
