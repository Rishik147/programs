package file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// Logger class to write messages to a log file
class Logger {

    private final String path;

    // Constructor: initializes the logger with a file path and creates the file if it doesn't exist
    public Logger(String path) throws IOException {
        this.path = path;
        File file = new File(path);
        if (!file.exists())
            file.createNewFile();
    }

    // log method: appends a message to the log file
    // Note: FileWriter(path, true) opens file in append mode, preserving existing content
    public void log(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.newLine();
            writer.write(message);
        } catch (Exception e) {
            System.out.println("Exception occurred while logging " + e);
        }
    }
}

public class CustomLoggerRunner {
    public static void main(String[] args) throws IOException {
        // Create a logger instance that logs to myLogs.txt
        Logger logger = new Logger("myLogs.txt");
        // Log multiple messages to the file
        logger.log("log message 1");
        logger.log("log message 2");
        logger.log("log message 3");
    }
}
