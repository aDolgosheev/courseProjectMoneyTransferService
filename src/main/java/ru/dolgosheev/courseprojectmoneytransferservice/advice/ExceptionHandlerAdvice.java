package ru.dolgosheev.courseprojectmoneytransferservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.dolgosheev.courseprojectmoneytransferservice.exception.ErrorTransferOrConfirmException;
import ru.dolgosheev.courseprojectmoneytransferservice.exception.InvalidDataException;
import ru.dolgosheev.courseprojectmoneytransferservice.logger.Logger;
import ru.dolgosheev.courseprojectmoneytransferservice.logger.LoggerImpl;

import java.util.concurrent.atomic.AtomicInteger;

public class ExceptionHandlerAdvice {
    private final Logger logger;
    private final AtomicInteger errorId;

    public ExceptionHandlerAdvice() {
        logger = LoggerImpl.getInstance();
        errorId = new AtomicInteger(0);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ErrorResponse> handler(InvalidDataException e) {
        logger.log(e.getMessage());
        return buildResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ErrorTransferOrConfirmException.class)
    public ResponseEntity<ErrorResponse> handler(ErrorTransferOrConfirmException e) {
        logger.log(e.getMessage());
        return buildResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> buildResponse( String msg, HttpStatus status) {
        return switch (status) {
            case BAD_REQUEST -> new ResponseEntity<>(
                    new ErrorResponse(
                            "BAD_REQUEST : " + msg,
                            errorId.incrementAndGet()
                    ),
                    HttpStatus.BAD_REQUEST
            );
            case INTERNAL_SERVER_ERROR -> new ResponseEntity<>(
                    new ErrorResponse(
                            "INTERNAL_SERVER_ERROR : " + msg,
                            errorId.incrementAndGet()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
            default -> new ResponseEntity<>(
                    new ErrorResponse("BAD_GATEWAY", errorId.incrementAndGet()),
                    HttpStatus.BAD_GATEWAY
            );
        };
    }
}
