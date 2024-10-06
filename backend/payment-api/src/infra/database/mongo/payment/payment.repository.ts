import { Inject, Injectable } from '@nestjs/common';
import { InjectModel } from '@nestjs/mongoose';
import { Payment } from 'src/domain/entities/payment.entity';
import { PaymentGateway } from 'src/domain/gateways/payment.gateway';
import { PaymentModel } from './models/payment.model';
import { Model } from 'mongoose';
import { PaymentEntityToModelMapper } from './mappers/payment-entity-to-model.mapper';
import { PaymentModelToEntityMapper } from './mappers/payment-model-to-entity.mapper';

@Injectable()
export class PaymentRepository implements PaymentGateway {
  public constructor(
    @InjectModel(PaymentModel.name) private paymentModel: Model<PaymentModel>,
  ) {}

  async create(payment: Payment): Promise<void> {
    const aModel = PaymentEntityToModelMapper.toModel(payment);
    await this.paymentModel.create(aModel);
  }

  async update(payment: Payment): Promise<void> {
    const aModel = PaymentEntityToModelMapper.toModel(payment);
    await this.paymentModel.updateOne({ id: aModel.id }, aModel);
  }

  async findById(id: string): Promise<Payment | null> {
    const aModel = await this.paymentModel.findOne({ id });

    if (!aModel) {
      return null;
    }

    const anEntity = PaymentModelToEntityMapper.toEntity(aModel);

    return anEntity;
  }

  async findByOriginId(originId: string): Promise<Payment | null> {
    const aModel = await this.paymentModel.findOne({ originId });

    if (!aModel) {
      return null;
    }

    const anEntity = PaymentModelToEntityMapper.toEntity(aModel);

    return anEntity;
  }
}
