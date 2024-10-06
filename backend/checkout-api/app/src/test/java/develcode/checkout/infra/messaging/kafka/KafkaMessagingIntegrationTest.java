package develcode.checkout.infra.messaging.kafka;

import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import develcode.checkout.domain.shared.events.Event;
import develcode.checkout.handlers.EventHandler;
import develcode.checkout.infra.annotations.KafkaIntegrationTest;

@KafkaIntegrationTest
public class KafkaMessagingIntegrationTest {

    @Autowired
    private KafkaMessaging messaging;

    @Test
    public void givenValidTopicAndHandler_whenSubscribe_thenSubscribeToTopicWithHandler() {

        final var expectedEvent = new TestEvent();

        this.messaging.subscribe("test", new TestEventHandler());

        final var eventHandlers = this.messaging.getHandlers().get(expectedEvent.getName());

        assertEquals(1, this.messaging.getHandlers().size());
        assertEquals(1, eventHandlers.size());
    }

    @Test
    public void givenValidEvent_whenPublish_thenPublishToTopic() {

        final var expectedEvent = new TestEvent();
        expectedEvent.setPayload("Test 2");

        this.messaging.publish(expectedEvent);
    }

    private class TestEvent extends Event implements Serializable {

        private static final long serialVersionUID = 1L;

        public TestEvent() {
            super("test");
        }
    }

    private class TestEventHandler implements EventHandler {

        @Override
        public void handle(final Event event) {
            assertTrue(event instanceof TestEvent);
            assertEquals("Test 2", ((TestEvent) event).getPayload());
        }
    }
}
