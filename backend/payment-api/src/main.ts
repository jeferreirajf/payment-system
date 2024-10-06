import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { Logger } from '@nestjs/common';

async function bootstrap() {
  const port = process.env.PORT || 3030;

  const app = await NestFactory.create(AppModule);

  const logger = new Logger('bootstrap');

  await app.listen(port, () => {
    logger.log(`Server is running on PORT ${port}`);
  });
}
bootstrap();
