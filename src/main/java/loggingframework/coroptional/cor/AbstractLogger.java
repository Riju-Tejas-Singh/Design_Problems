package loggingframework.coroptional.cor;

import loggingframework.entities.LogMessage;
import loggingframework.enums.LogLevel;
import loggingframework.strategies.appender.LogAppender;

import java.util.List;

public abstract class AbstractLogger {

    protected AbstractLogger nextLogger;
    protected final List<LogAppender> appenders;

    public AbstractLogger(List<LogAppender> appenders) {
        this.appenders = appenders;
    }

    public void setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void log(LogMessage message) {

        if (canHandle(message.getLevel())) {
            write(message);
        }

        if (nextLogger != null) {
            nextLogger.log(message);
        }
    }

    protected void write(LogMessage message) {
        for (LogAppender appender : appenders) {
            appender.append(message);
        }
    }

    protected abstract boolean canHandle(LogLevel level);
}
