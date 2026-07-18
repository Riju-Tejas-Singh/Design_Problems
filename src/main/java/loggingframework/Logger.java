package loggingframework;

import loggingframework.entities.LogMessage;
import loggingframework.enums.LogLevel;
import loggingframework.strategies.appender.LogAppender;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Logger {

    private final String name;
    private volatile LogLevel level;
    private final List<LogAppender> appenders;

    public Logger(String name, LogLevel level) {
        this.name = name;
        this.level = level;
        this.appenders = new CopyOnWriteArrayList<>();
    }

    public void addAppender(LogAppender appender) {
        appenders.add(appender);
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void warn(String message) {
        log(LogLevel.WARN, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public void fatal(String message) {
        log(LogLevel.FATAL, message);
    }

    /**
     * Checks validity of requested log level then appends LogMessage to all appenders
     */
    private void log(LogLevel logLevel, String message) {

        if (logLevel.getSeverity() < level.getSeverity()) {
            return;
        }

        LogMessage logMessage = new LogMessage(logLevel, message);

        for (LogAppender appender : appenders) {
            appender.append(logMessage);
        }
    }

    public String getName() {
        return name;
    }
}
