package loggingframework.coroptional.cor;

import loggingframework.enums.LogLevel;
import loggingframework.strategies.appender.LogAppender;

import java.util.List;

public class WarnLogger extends  AbstractLogger {
    public WarnLogger(List<LogAppender> appenders) {
        super(appenders);
    }

    @Override
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.WARN;
    }
}
