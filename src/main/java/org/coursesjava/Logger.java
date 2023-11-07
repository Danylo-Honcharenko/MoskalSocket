package org.coursesjava;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    public static void write(String message, Exception exception) {
        try (PrintStream writeLog = new PrintStream(new FileOutputStream(Path.DEFAULT.getPath() + "moskalSocket.log", true))) {
            String currentDate = DateTimeFormatter.ofPattern("hh:mm:ss, MM.dd.yyyy")
                    .format(LocalDateTime.now());
            writeLog.println("[" + currentDate + "]" + " " + message);
            exception.printStackTrace(writeLog);
        } catch (IOException ex) {
            System.err.println("Error writing to log file: " + ex.getMessage());
        }
    }
}
