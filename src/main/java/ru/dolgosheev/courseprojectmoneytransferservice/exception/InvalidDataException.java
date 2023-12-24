package ru.dolgosheev.courseprojectmoneytransferservice.exception;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String message) {
        super(message);
    }
}
