import { Payment } from 'src/domain/entities/payment.entity';
import { PaymentPaidEvent } from 'src/domain/events/payment/payment-paid.event';
import { PaymentGateway } from 'src/domain/gateways/payment.gateway';
import { MessagingGateway } from 'src/infra/messaging/messaging';
import { InternalErrorUsecaseException } from 'src/usecases/shared/exceptions/internal-error.usecase.exception';
import { NotFoundUsecaseException } from 'src/usecases/shared/exceptions/not-found.usecase.exception';
import { Usecase } from 'src/usecases/usecase';
import { UUIDGenerator } from 'src/utils/uuid-generator';

export type ApprovePaymentUsecaseInputDto = {
  paymentId: string;
};

export type ApprovePaymentUsecaseCreatedDto = {
  paymentGateway: PaymentGateway;
  messagingGateway: MessagingGateway;
};

export class ApprovePaymentUsecase
  implements Usecase<ApprovePaymentUsecaseInputDto, void>
{
  private constructor(
    private readonly paymentGateway: PaymentGateway,
    private readonly messagingGateway: MessagingGateway,
  ) {}

  public static create({
    paymentGateway,
    messagingGateway,
  }: ApprovePaymentUsecaseCreatedDto): ApprovePaymentUsecase {
    if (!paymentGateway || !messagingGateway) {
      throw new InternalErrorUsecaseException(
        `Missing dependency while creating ${ApprovePaymentUsecase.name}`,
        ApprovePaymentUsecase.name,
      );
    }

    return new ApprovePaymentUsecase(paymentGateway, messagingGateway);
  }

  async execute(input: ApprovePaymentUsecaseInputDto): Promise<void> {
    const aPayment = await this.paymentGateway.findById(input.paymentId);

    if (!aPayment || aPayment === null) {
      throw new NotFoundUsecaseException(
        `Payment with id ${input.paymentId} not found while trying to approve`,
        ApprovePaymentUsecase.name,
      );
    }

    aPayment.approve();

    console.log('Approving payment', aPayment);

    await this.paymentGateway.update(aPayment);

    this.sendPaymentPaidEvent(aPayment);
  }

  private async sendPaymentPaidEvent(aPayment: Payment): Promise<void> {
    const anEvent = PaymentPaidEvent.create();
    anEvent.setPayload({
      orderId: aPayment.getOriginId(),
      paymentId: aPayment.getId(),
      status: aPayment.getStatus(),
    });

    await this.messagingGateway.publish('checkout', anEvent);
  }
}
