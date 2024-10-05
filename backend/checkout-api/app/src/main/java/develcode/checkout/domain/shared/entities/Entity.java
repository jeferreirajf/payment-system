package develcode.checkout.domain.shared.entities;

import java.time.Instant;

public abstract class Entity {

    private String id;
    private Instant createdAt;
    private Instant updatedAt;

    protected Entity(String id, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    protected void hashChanged() {
        this.updatedAt = Instant.now();
    }

    protected abstract void validate();
}
