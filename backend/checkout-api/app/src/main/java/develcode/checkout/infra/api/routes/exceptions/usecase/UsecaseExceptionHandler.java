package develcode.checkout.infra.api.routes.exceptions.usecase;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import develcode.checkout.domain.shared.exceptions.DomainException;
import develcode.checkout.infra.api.routes.shared.HttpResponseBody;
import develcode.checkout.usecases.shared.exceptions.UsecaseException;

@ControllerAdvice
public class UsecaseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsecaseException.class)
    public ResponseEntity<HttpResponseBody> handle(DomainException e) {
        final var responseBody = HttpResponseBody.buildError(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        final var response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);

        return response;
    }
}
