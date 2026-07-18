package loggingframework.entities;

import loggingframework.enums.LogLevel;

import java.time.LocalDateTime;

public class LogMessage {

    private final LogLevel level;
    private final String message;
    private final LocalDateTime timestamp;

    public LogMessage(LogLevel level, String message) {
        this.level = level;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    // getters
    public LogLevel getLevel() {
        return level;
    }
    public String getMessage() {
        return message;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

}
