package develcode.checkout.domain.valueobjects;

import develcode.checkout.domain.shared.exceptions.DomainException;

public enum OrderStatus {
    PENDING("PENDING"), APPROVED("APPROVED"), REJECTED("REJECTED");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public static OrderStatus from(final String status) {
        for (var aStatus : OrderStatus.values()) {
            if (aStatus.status.equals(status)) {
                return aStatus;
            }
        }

        throw new DomainException(
                "Invalid order status. The status should be one of the following: PENDING, APPROVED, REJECTED");
    }
}
