package develcode.checkout.infra.api.routes.order.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import develcode.checkout.domain.gateways.OrderGateway;
import develcode.checkout.infra.api.routes.Route;
import develcode.checkout.infra.api.routes.order.create.dtos.CreateOrderRequest;
import develcode.checkout.infra.api.routes.order.create.dtos.CreateOrderResponse;
import develcode.checkout.infra.api.routes.order.create.mappers.CreateOrderRequestToInputMapper;
import develcode.checkout.infra.messaging.Messaging;
import develcode.checkout.usecases.order.create.CreateOrderUsecase;

@RestController
@RequestMapping("/orders")
public class CreateOrderRoute implements Route<CreateOrderRequest, CreateOrderResponse> {

    @Autowired
    private OrderGateway orderGateway;

    @Autowired
    private Messaging messaging;

    @Override
    @PostMapping
    public CreateOrderResponse handle(@RequestBody() final CreateOrderRequest request) {
        final var anUsecase = CreateOrderUsecase.create(orderGateway, messaging);

        final var input = CreateOrderRequestToInputMapper.map(request);

        final var output = anUsecase.execute(input);

        final var response = new CreateOrderResponse(output);

        return response;
    }

}
