package loggingframework.strategies.appender;

import loggingframework.entities.LogMessage;
import loggingframework.strategies.formatter.LogFormatter;
import loggingframework.strategies.formatter.SimpleTextFormatter;

import java.io.FileWriter;
import java.io.IOException;

public class FileAppender implements LogAppender {

    private final FileWriter writer;
    private LogFormatter formatter;

    public FileAppender(String filePath) {
        try {
            // true => append mode
            this.writer = new FileWriter(filePath, true);
        } catch (IOException e) {
            throw new RuntimeException("Unable to create FileAppender", e);
        }
    }

    @Override
    public void append(LogMessage message) {
        try {
            writer.write(formatter.format(message));
            writer.write(System.lineSeparator());
            writer.flush();   // Immediately write to disk
        } catch (IOException e) {
            throw new RuntimeException("Failed to write log to file", e);
        }
    }

    @Override
    public void setFormatter(LogFormatter formatter) {
        this.formatter = formatter;
    }
}
