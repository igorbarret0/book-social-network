package com.barreto.book_social_newtwork.handler;

import com.barreto.book_social_newtwork.exception.OperationNotPermittedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ExceptionResponse> handleException(LockedException lockedException) {

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                        new ExceptionResponse(
                               BusinessErrorCodes.ACCOUNT_LOCKED.getCode(),
                                BusinessErrorCodes.ACCOUNT_LOCKED.getDescription(),
                                lockedException.getMessage()
                        )
                );
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExceptionResponse> disableHandlerException(DisabledException disabledException) {

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                        new ExceptionResponse(
                                BusinessErrorCodes.ACCOUNT_DISABLED.getCode(),
                                BusinessErrorCodes.ACCOUNT_DISABLED.getDescription(),
                                disabledException.getMessage()
                        )
                );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> badCredentialsHandlerException(BadCredentialsException badCredentialsException) {

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                        new ExceptionResponse(
                                BusinessErrorCodes.BAD_CREDENTIALS.getCode(),
                                BusinessErrorCodes.BAD_CREDENTIALS.getDescription(),
                                badCredentialsException.getMessage()
                        )
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> methodArgumentNotValidHandlerException(MethodArgumentNotValidException exception) {

        Set<String> errors = new HashSet<>();
        exception.getBindingResult().getAllErrors()
                .forEach(
                        error -> {
                            var errorMessage = error.getDefaultMessage();
                            errors.add(errorMessage);
                        }
                );

        var excepResponse = new ExceptionResponse();
        excepResponse.setValidationErrors(errors);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        excepResponse
                );
    }

    @ExceptionHandler(OperationNotPermittedException.class)
    public ResponseEntity<ExceptionResponse> operationNotPermittedHandlerException(
            OperationNotPermittedException exception
    ) {

        var excepResponse = new ExceptionResponse();
        excepResponse.setError(exception.getMessage());

       return ResponseEntity
               .status(HttpStatus.BAD_REQUEST)
               .body(
                       excepResponse
               );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> exceptionHandler(Exception exception) {

        var excepResponse = new ExceptionResponse();
        excepResponse.setBusinessErrorDescription("Internal server error. Contact admin");
        excepResponse.setError(exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        excepResponse
                );
    }

}
