package loggingframework.strategies.appender;

import loggingframework.entities.LogMessage;
import loggingframework.strategies.formatter.LogFormatter;
import loggingframework.strategies.formatter.SimpleTextFormatter;

public class ConsoleAppender implements LogAppender {

    private LogFormatter formatter;

    @Override
    public void append(LogMessage message) {
        System.out.println(formatter.format(message));
    }

    @Override
    public void setFormatter(LogFormatter formatter) {
        this.formatter = formatter;
    }
}