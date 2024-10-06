package develcode.checkout.infra.api.routes.exceptions.domain;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import develcode.checkout.domain.shared.exceptions.DomainException;
import develcode.checkout.infra.api.routes.shared.HttpResponseBody;

@ControllerAdvice
public class DomainExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<HttpResponseBody> handle(DomainException e) {
        this.logger.error(e.getMessage(), e);

        final var responseBody = HttpResponseBody.buildError(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        final var response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);

        return response;
    }
}
