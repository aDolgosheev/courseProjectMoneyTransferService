package ru.dolgosheev.courseprojectmoneytransferservice.logger;

public interface Logger {

    void log(String message);

    static Logger getInstance() {
        return null;
    }
}
