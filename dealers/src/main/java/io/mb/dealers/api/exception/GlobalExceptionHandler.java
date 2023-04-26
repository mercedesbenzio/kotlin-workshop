package io.mb.dealers.api.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private static final Logger log = LogManager.getLogger(GlobalExceptionHandler.class);

    // We are here defining a custom exception handler for some custom exception on our application.
    // Several other approaches can be used (see. ResponseStatusException).

    private ErrorResponse handleException(
            final Exception ex, final HttpStatus status, final HttpServletRequest httpServletRequest
    ) {
        log.error("Handling exception of class " + ex.getClass().getSimpleName(), ex);

        return new ErrorResponse(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                httpServletRequest.getRequestURI()
        );
    }

    @ExceptionHandler({DealerNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse dealerNotFoundExceptionHandler(
            final DealerNotFoundException ex, final HttpServletRequest httpServletRequest
    ) {
        return handleException(ex, HttpStatus.NOT_FOUND, httpServletRequest);
    }

    @ExceptionHandler({DealerAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse dealerAlreadyExistsExceptionHandler(
            final DealerAlreadyExistsException ex, final HttpServletRequest httpServletRequest
    ) {
        return handleException(ex, HttpStatus.CONFLICT, httpServletRequest);
    }
}
