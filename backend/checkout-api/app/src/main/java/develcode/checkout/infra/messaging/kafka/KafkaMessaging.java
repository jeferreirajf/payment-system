package develcode.checkout.infra.messaging.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import develcode.checkout.domain.shared.events.Event;
import develcode.checkout.handlers.EventHandler;
import develcode.checkout.infra.messaging.Messaging;

@EnableKafka
@Service
public class KafkaMessaging implements Messaging {

    @Autowired
    private KafkaTemplate<String, Object> template;

    private final HashMap<String, List<EventHandler>> handlers = new HashMap<>();

    @Override
    public void publish(final String topic, final Event event) {
        this.template.send(topic, event);
    }

    @Override
    public void subscribe(final String topic, final EventHandler handler) {
        if (!this.handlers.containsKey(topic)) {
            this.handlers.put(topic, List.of(handler));
        } else {
            this.handlers.get(topic).add(handler);
        }
    }

    @KafkaListener(topics = {"checkout", "test"}, groupId = "develcode")
    public void consume(final Event event) {
        final var eventHandlers = this.handlers.get(event.getName());

        if (eventHandlers != null) {
            eventHandlers.forEach(handler -> handler.handle(event));
        }
    }

    public Map<String, List<EventHandler>> getHandlers() {
        return this.handlers;
    }
}
