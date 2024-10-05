package develcode.checkout.usecases.order.list;

import java.util.Arrays;

import develcode.checkout.domain.gateways.OrderGateway;
import develcode.checkout.usecases.Usecase;
import develcode.checkout.usecases.order.list.dtos.ListOrdersInputDto;
import develcode.checkout.usecases.order.list.dtos.ListOrdersOutputDto;
import develcode.checkout.usecases.shared.exceptions.InternalUsecaseException;

public class ListOrdersUsecase implements Usecase<ListOrdersInputDto, ListOrdersOutputDto> {

    private final OrderGateway orderGateway;

    private ListOrdersUsecase(final OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public static ListOrdersUsecase create(final OrderGateway orderGateway) {

        if (orderGateway == null) {
            throw new InternalUsecaseException(
                    "The orderGateway dependency must be provided while creating ListOrdersUsecase"
            );
        }

        return new ListOrdersUsecase(orderGateway);
    }

    @Override
    public ListOrdersOutputDto execute(final ListOrdersInputDto input) {
        final var anOrders = orderGateway.list();

        final var output = ListOrdersOutputDto.from(Arrays.asList(anOrders));

        return output;
    }
}
