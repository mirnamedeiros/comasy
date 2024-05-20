package pds.comasy.exceptions.handler;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pds.comasy.exceptions.*;
import pds.comasy.exceptions.model.ApiError;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler({
            AuthenticationException.class,
            EntityAlreadyExistsException.class,
            EntitySaveFailureException.class,
            FailedToDeleteException.class,
            InvalidFieldException.class,
            NotFoundException.class
    })
    public ResponseEntity<ApiError> handleSpecificException(Exception ex) {
        HttpStatus status;
        if (ex instanceof AuthenticationException) {
            status = HttpStatus.UNAUTHORIZED;
        } else if (ex instanceof EntityAlreadyExistsException) {
            status = HttpStatus.CONFLICT;
        } else if (ex instanceof EntitySaveFailureException || ex instanceof InvalidFieldException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof FailedToDeleteException) {
            status = HttpStatus.CONFLICT;
        } else if (ex instanceof NotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return buildResponse(status, ex.getMessage());
    }

    private ResponseEntity<ApiError> buildResponse(HttpStatus status, String message) {
        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .code(status.value())
                .status(status.getReasonPhrase())
                .errors(Collections.singletonList(message))
                .build();
        return new ResponseEntity<>(apiError, status);
    }
}