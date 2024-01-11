package ru.dolgosheev.courseprojectmoneytransferservice.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class LoggerImpl implements Logger {

    private static Logger instance = null;

    private LoggerImpl() {
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new LoggerImpl();
        }
        return instance;
    }

    @Override
    public void log(String message) {
        try {
            FileWriter writer = new FileWriter("log.txt", true);
            writer.append("[")
                    .append(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)))
                    .append(":")
                    .append(LocalTime.now().format(DateTimeFormatter.ofPattern("HH.mm.ss.nnn")))
                    .append("] ")
                    .append(message)
                    .append("\n");
            writer.flush();
        } catch (IOException e) {
            System.err.println("Log Error " + message);
        }
    }
}
