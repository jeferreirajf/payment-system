package develcode.checkout.domain.shared.events;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Event implements Serializable {

    @JsonProperty("id")
    private final String id;
    @JsonProperty("name")
    private final String name;
    @JsonProperty("payload")
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
