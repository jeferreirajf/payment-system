package develcode.checkout.infra.api.routes.order.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import develcode.checkout.domain.gateways.OrderGateway;
import develcode.checkout.infra.api.routes.Route;
import develcode.checkout.infra.api.routes.order.list.dtos.ListOrderRequest;
import develcode.checkout.infra.api.routes.order.list.dtos.ListOrderResponse;
import develcode.checkout.infra.api.routes.order.list.presenter.ListOrderPresenter;
import develcode.checkout.usecases.order.list.ListOrdersUsecase;
import develcode.checkout.usecases.order.list.dtos.ListOrdersInputDto;

@RestController
@RequestMapping("/orders")
public class ListOrderRoute implements Route<ListOrderRequest, ListOrderResponse> {

    @Autowired
    private OrderGateway orderGateway;

    @GetMapping
    @Override
    public ListOrderResponse handle(final ListOrderRequest request) {
        final var anUsecase = ListOrdersUsecase.create(orderGateway);

        final var input = new ListOrdersInputDto();

        final var output = anUsecase.execute(input);

        final var response = ListOrderPresenter.present(output);

        return response;
    }
}
