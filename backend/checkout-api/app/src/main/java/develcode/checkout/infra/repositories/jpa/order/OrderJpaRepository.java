package develcode.checkout.infra.repositories.jpa.order;

import org.springframework.data.jpa.repository.JpaRepository;

import develcode.checkout.infra.repositories.jpa.order.models.OrderJpaModel;

public interface OrderJpaRepository extends JpaRepository<OrderJpaModel, String> {

}
