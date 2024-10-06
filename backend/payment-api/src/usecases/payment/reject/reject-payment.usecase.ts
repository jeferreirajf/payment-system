import { Payment } from 'src/domain/entities/payment.entity';
import { PaymentRejectedEvent } from 'src/domain/events/payment/payment-rejected.event';
import { PaymentGateway } from 'src/domain/gateways/payment.gateway';
import { MessagingGateway } from 'src/infra/messaging/messaging';
import { InternalErrorUsecaseException } from 'src/usecases/shared/exceptions/internal-error.usecase.exception';
import { NotFoundUsecaseException } from 'src/usecases/shared/exceptions/not-found.usecase.exception';
import { Usecase } from 'src/usecases/usecase';

export type RejectPaymentUsecaseInputDto = {
  paymentId: string;
};

export type RejectPaymentUsecaseCreatedDto = {
  paymentGateway: PaymentGateway;
  messagingGateway: MessagingGateway;
};

export class RejectPaymentUsecase
  implements Usecase<RejectPaymentUsecaseInputDto, void>
{
  private constructor(
    private readonly paymentGateway: PaymentGateway,
    private readonly messagingGateway: MessagingGateway,
  ) {}

  public static create({
    paymentGateway,
    messagingGateway,
  }: RejectPaymentUsecaseCreatedDto): RejectPaymentUsecase {
    if (!paymentGateway || !messagingGateway) {
      throw new InternalErrorUsecaseException(
        `Missing dependency while creating ${RejectPaymentUsecase.name}`,
        RejectPaymentUsecase.name,
      );
    }

    return new RejectPaymentUsecase(paymentGateway, messagingGateway);
  }

  async execute(input: RejectPaymentUsecaseInputDto): Promise<void> {
    const aPayment = await this.paymentGateway.findByOriginId(input.paymentId);

    if (!aPayment || aPayment === null) {
      throw new NotFoundUsecaseException(
        `Payment with paymentId ${input.paymentId} not found while trying to reject a payment`,
        RejectPaymentUsecase.name,
      );
    }

    aPayment.reject();

    await this.paymentGateway.update(aPayment);

    this.sendRejectPaymentEvent(aPayment);
  }

  private async sendRejectPaymentEvent(aPayment: Payment): Promise<void> {
    const anEvent = PaymentRejectedEvent.create();
    anEvent.setPayload({
      orderId: aPayment.getOriginId(),
      paymentId: aPayment.getId(),
      status: aPayment.getStatus(),
    });

    await this.messagingGateway.publish('checkout', anEvent);
  }
}
