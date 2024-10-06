package develcode.checkout.domain.shared.events;

import java.util.UUID;

public abstract class Event {

    private final String id;
    private final String name;
    private Object payload;

    protected Event(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.payload = null;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public <T> T getPayload(Class<T> clazz) throws ClassCastException {
        return clazz.cast(payload);
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
