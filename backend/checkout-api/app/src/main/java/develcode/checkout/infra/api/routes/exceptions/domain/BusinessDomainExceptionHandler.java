package develcode.checkout.infra.api.routes.exceptions.domain;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import develcode.checkout.domain.shared.exceptions.BusinessDomainException;
import develcode.checkout.infra.api.routes.shared.HttpResponseBody;

@ControllerAdvice
public class BusinessDomainExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessDomainException.class)
    public ResponseEntity<HttpResponseBody> handle(BusinessDomainException e) {
        this.logger.error(e.getMessage(), e);

        final var responseBody = HttpResponseBody.buildError(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        final var response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);

        return response;
    }
}
