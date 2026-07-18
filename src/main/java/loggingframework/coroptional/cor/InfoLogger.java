package loggingframework.coroptional.cor;

import loggingframework.enums.LogLevel;
import loggingframework.strategies.appender.LogAppender;

import java.util.List;

public class InfoLogger extends AbstractLogger {
    public InfoLogger(List<LogAppender> appenders) {
        super(appenders);
    }

    @Override
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.INFO;
    }
}
