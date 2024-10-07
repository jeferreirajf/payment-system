import { z } from 'zod';
import { Payment, PaymentStatus } from '../entities/payment.entity';
import { Validator } from '../shared/validator';
import { ValidationDomainException } from '../shared/exceptions/validation.domain.exception';

export class PaymentZodValidator implements Validator<Payment> {
  private constructor() {}

  public static create(): PaymentZodValidator {
    return new PaymentZodValidator();
  }

  public validate(input: Payment): void {
    const schema = this.getSchema();

    try {
      schema.parse(input);
    } catch (error) {
      const err = error as z.ZodError;

      const messages = Array.from(err.errors).map((error) => {
        return `${error.path} ${error.message}`;
      });

      const message = messages.join('. ');

      throw new ValidationDomainException(message);
    }
  }

  private getSchema() {
    const schema = z.object({
      id: z.string().uuid(),
      originId: z.string(),
      amount: z.number().min(0.01),
      status: z.nativeEnum(PaymentStatus),
      paymentData: z.object({}),
      paymentResponseDate: z.date().optional(),
      createdAt: z.date(),
      updatedAt: z.date(),
    });

    return schema;
  }
}
