# Checkout-Payment API

Neste projeto foi desenvolvido uma API para simular um checkout de pagamento, onde é possível criar um pedido e listar pedidos.

A principal ideia era manter uma arquitetura escalável e bem dividida, para que seja possível adicionar novas funcionalidades sem muita dificuldade.

## Frontend

O frontend foi desenvolvido em ReactJS onde a única página deveria enviar novos pedidos e exibir os pedidos já criados.

![Alt text](frontend.png)

## Backend

O backend foi dividido em três serviços: - Checkout: Onde é possível criar um pedido e listar pedidos. - Payment: Onde é possível simular um pagamento. - GoPay: Simulador de gateway de pagamento que responde por callback.

Inicialmente a modelagem seguindo DDD foi feita e então uma modelagel de solução foi criada. Abaixo seguem os resultados dessas etapas:

![Alt text](design-models/solution-design.png)
![Alt text](design-models/domain-design.png)

Todos os projetos possuem uma pasta `.http` onde é possível testar os endpoints.

### Checkout-api

A API de checkout foi desenvolvida com JAVA/Spring Boot e possui os seguintes endpoints:

-   POST /orders: Cria um novo pedido
-   GET /orders: Lista todos os pedidos

A API consome o tópico `checkout` no Kafka e publica no tópico `payment`. Os eventos são:

-   OrderCreatedEvent: Evento que é publicado quando um novo pedido é criado.
-   PaymentPaidEvent: Evento que é consumido quando um pagamento é aprovado.
-   PaymentRejectedEvent: Evento que é consumido quando um pagamento é rejeitado.

### Payment-api

A API de pagamento foi desenvolvida com NodeJS/NestJS e possui os seguintes endpoints:

-   POST /webhook/payment: Webhook para receber notificações de pagamento do gateway.

A API consome o tópico `payment` no Kafka e publica no tópico `checkout`. Os eventos são:

-   OrderCreatedEvent: Evento que é consumido quando um pedido é criado.
-   PaymentPaidEvent: Evento que é publicado quando um pagamento é aprovado.
-   PaymentRejectedEvent: Evento que é publicado quando um pagamento é rejeitado.

As variáveis de ambiente do sistema são:
```bash
PORT=3030
MONGO_DATABASE_URL=mongodb://develcode:develcode@localhost:27017/develcode
PAYMENT_CALLBACK_URL=http://localhost:3030/webhook/payment
KAFKA_URL=localhost:9092
GO_PAYMENT_URL=http://localhost:6001
```

### GoPay-api

A API de gateway de pagamento foi desenvolvida com Golang e possui os seguintes endpoints:

-   POST /orders/place: Simula um pagamento e responde por callback 5 segundos depois.

### Executando o projeto

Para executar o projeto, é necessário ter o Docker e Docker Compose instalados. O primeiro passo é

```bash
docker compose up -d
```

Após isso, é necessário acessar a pasta `backend/checkout-api` e executar o comando:

```bash
gradle run
```

E então acessar a pasta `backend/payment-api` e executar o comando:

```bash
npm install
npm run start:dev
```

Por último, acessar a pasta `backend/gopay-api` e executar o comando:

```bash
go run main.go
```

Note que é necessário possuir o Gradle, Java 21, NodeJS e Go instalados.

A partir daí, basta executar os endpoints em `/checkout-api/.http`.

Para acessar o Kafka, basta ir em `http://localhost:9021`.
