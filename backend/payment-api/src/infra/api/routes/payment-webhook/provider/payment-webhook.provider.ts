import { BadRequestException, Injectable } from '@nestjs/common';
import { PaymentStatus } from 'src/domain/entities/payment.entity';
import { PaymentGateway } from 'src/domain/gateways/payment.gateway';
import { PaymentRepository } from 'src/infra/database/mongo/payment/payment.repository';
import { MessagingGateway } from 'src/infra/messaging/messaging';
import { ApprovePaymentUsecase } from 'src/usecases/payment/approve/approve-payment.usecase';
import { RejectPaymentUsecase } from 'src/usecases/payment/reject/reject-payment.usecase';
import { Usecase } from 'src/usecases/usecase';

export type PaymentWebhookProviderInputDto = {
  paymentId: string;
  status: string;
};

@Injectable()
export class PaymentWebhookProvider {
  private usecaseMap: Map<string, Usecase<any, any>>;

  public constructor(
    paymentGateway: PaymentRepository,
    messagingGateway: MessagingGateway,
  ) {
    const approveUsecase = ApprovePaymentUsecase.create({
      paymentGateway,
      messagingGateway,
    });

    const rejectusecase = RejectPaymentUsecase.create({
      paymentGateway,
      messagingGateway,
    });

    this.usecaseMap = new Map();

    this.usecaseMap.set(PaymentStatus.APPROVED, approveUsecase);
    this.usecaseMap.set(PaymentStatus.REJECTED, rejectusecase);
  }

  public async execute(input: PaymentWebhookProviderInputDto): Promise<void> {
    const usecase = this.usecaseMap.get(input.status);

    if (!usecase) {
      throw new BadRequestException(
        `Invalid status ${input.status} while trying to process payment webhook`,
      );
    }

    await usecase.execute(input);
  }
}
