package lt.dagaz.boot.challenge;

import lt.dagaz.boot.challenge.transactions.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> unhandledError(Exception ex, WebRequest request) {
        log.info("Intended to add duplicate to the system", ex);
        return new ResponseEntity<>(new ErrorResponse("Issue With the service. Please try again later"), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<ErrorResponse> handleDuplicateEntry(Exception ex, WebRequest request) {
        log.info("Intended to add duplicate to the system", ex);
        return new ResponseEntity<>(new ErrorResponse("Duplicate entry."), CONFLICT);
    }
}
