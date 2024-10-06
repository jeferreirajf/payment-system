package develcode.checkout.infra.repositories.jpa.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import develcode.checkout.domain.entities.Order;
import develcode.checkout.domain.gateways.OrderGateway;
import develcode.checkout.infra.repositories.jpa.order.mappers.OrderModelToEntityMapper;
import develcode.checkout.infra.repositories.jpa.order.models.OrderJpaModel;

@Service
public class OrderJpaGateway implements OrderGateway {

    @Autowired
    private OrderJpaRepository orderRepository;

    @Override
    public void create(final Order anOrder) {
        final var aModel = OrderJpaModel.from(anOrder);
        orderRepository.save(aModel);
    }

    @Override
    public void update(final Order anOrder) {
        final var existentModel = orderRepository.findById(anOrder.getId()).orElse(null);

        if (existentModel == null) {
            return;
        }

        final var aModel = OrderJpaModel.from(anOrder);
        orderRepository.save(aModel);
    }

    @Override
    public Order findById(final String id) {
        final var aModel = orderRepository.findById(id).orElse(null);

        if (aModel == null) {
            return null;
        }

        final var anEntity = OrderModelToEntityMapper.map(aModel);

        return anEntity;
    }

    @Override
    public Order[] list() {
        final var aModels = orderRepository.findAll();
        final var anEntities = aModels.stream()
                .map(OrderModelToEntityMapper::map)
                .toArray(Order[]::new);

        return anEntities;
    }

}
