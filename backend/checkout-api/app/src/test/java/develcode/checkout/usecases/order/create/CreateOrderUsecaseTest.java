package develcode.checkout.usecases.order.create;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;

import develcode.checkout.domain.entities.Order;
import develcode.checkout.domain.events.OrderCreatedEvent;
import develcode.checkout.domain.gateways.OrderGateway;
import develcode.checkout.infra.messaging.Messaging;
import develcode.checkout.usecases.order.create.dtos.CreateOrderInputDto;
import develcode.checkout.usecases.order.create.dtos.CustomerDto;
import develcode.checkout.usecases.order.create.dtos.PaymentDataDto;
import develcode.checkout.usecases.order.create.dtos.ProductDto;
import develcode.checkout.usecases.shared.exceptions.InternalUsecaseException;

@Testable
public class CreateOrderUsecaseTest {

    @Mock
    private Messaging messagingMock;
    @Mock
    private OrderGateway orderGatewayMock;

    private CreateOrderInputDto input;

    @BeforeEach
    public void setUp() {
        final var customer = new CustomerDto("John Doe", "john@doe.com");
        final var paymentData = new PaymentDataDto(
                "1234567890123456",
                "John Doe",
                "12/2023",
                "123");
        final var product = new ProductDto("Product", "Product description", 125.25f, 2);

        input = new CreateOrderInputDto(customer, paymentData, product);

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenValidDependencies_whenCreatingCreateOrderUsecase_thenItShouldReturnAnUsecase() {
        final var anUsecase = CreateOrderUsecase.create(orderGatewayMock, messagingMock);

        assertNotNull(anUsecase);
    }

    @Test
    public void givenAnyNullDependency_whenCreatingCreateOrderUsecase_thenItShouldThrowAnException() {
        var anError = assertThrows(InternalUsecaseException.class, () -> {
            CreateOrderUsecase.create(orderGatewayMock, null);
        });

        assertNotNull(anError);
        assertEquals(anError.getMessage(), "The messaging and orderGateway dependencies must be provided while creating CreateOrderUsecase");

        anError = assertThrows(InternalUsecaseException.class, () -> {
            CreateOrderUsecase.create(null, messagingMock);
        });

        assertNotNull(anError);
        assertEquals(anError.getMessage(), "The messaging and orderGateway dependencies must be provided while creating CreateOrderUsecase");
    }

    @Test
    public void givenValidInput_whenExecutingCreateOrderUsecase_thenItShouldReturnAnOrderId() {
        final var anUsecase = CreateOrderUsecase.create(orderGatewayMock, messagingMock);

        final var orderId = anUsecase.execute(input);

        final var uuidRegex = "^[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89ab][a-f0-9]{3}-[a-f0-9]{12}$";

        assertNotNull(orderId);
        assertTrue(orderId.matches(uuidRegex));
    }

    @Test
    public void givenValidInput_whenExecutingCreateOrderUsecase_thenItShouldCreateGatewayAndPublishEvent() {
        final var messagingSpy = spy(Messaging.class);
        final var orderGatewaySpy = spy(OrderGateway.class);

        final var anUsecase = CreateOrderUsecase.create(orderGatewaySpy, messagingSpy);

        anUsecase.execute(input);

        verify(messagingSpy).publish(any(OrderCreatedEvent.class));
        verify(orderGatewaySpy).create(any(Order.class));
    }
}
