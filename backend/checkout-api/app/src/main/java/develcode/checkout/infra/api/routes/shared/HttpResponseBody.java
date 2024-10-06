package develcode.checkout.infra.api.routes.shared;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HttpResponseBody implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("message")
    private String message;
    @JsonProperty("status_code")
    private int statusCode;
    @JsonProperty("timestamp")
    private Instant timestamp;
    @JsonProperty("data")
    private Object data;

    public HttpResponseBody(final String message, final int statusCode, final Object data) {
        this.message = message;
        this.statusCode = statusCode;
        this.data = data;
        this.timestamp = Instant.now();
    }

    public static HttpResponseBody buildError(final String errorMessage, int errorCode) {
        return new HttpResponseBody(errorMessage, errorCode, null);
    }

    public static HttpResponseBody buildSuccess(final String message, final Object data) {
        return new HttpResponseBody(message, 200, data);
    }
}
