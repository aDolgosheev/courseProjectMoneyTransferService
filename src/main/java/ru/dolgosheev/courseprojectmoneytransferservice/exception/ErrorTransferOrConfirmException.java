package ru.dolgosheev.courseprojectmoneytransferservice.exception;

public class ErrorTransferOrConfirmException extends RuntimeException {
    public ErrorTransferOrConfirmException(String message) {
        super(message);
    }
}