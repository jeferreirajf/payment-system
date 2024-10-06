import { DomainException } from '../shared/exceptions/domain.exception';
import { Validator } from '../shared/validator';
import { PaymentData } from '../value-objects/payment-data.value-object';

import { z } from 'zod';

export class PaymentDataZodValidator implements Validator<PaymentData> {
  private constructor() {}

  public static create(): PaymentDataZodValidator {
    return new PaymentDataZodValidator();
  }

  public validate(input: PaymentData): void {
    const schema = this.getSchema();

    try {
      schema.parse({
        cardNumber: input.getCardNumber(),
        cardHolder: input.getCardHolder(),
        expirationDate: input.getExpirationDate(),
        cvv: input.getCvv(),
      });
    } catch (error) {
      const err = error as z.ZodError;

      const messages = Array.from(err.errors).map((error) => {
        return error.message;
      });

      const message = messages.join('. ');

      throw new DomainException(message);
    }
  }

  private getSchema() {
    const schema = z.object({
      cardNumber: z.string().length(16),
      cardHolder: z.string().min(2),
      expirationDate: z.string(),
      cvv: z.string().max(4).min(3),
    });

    return schema;
  }
}
