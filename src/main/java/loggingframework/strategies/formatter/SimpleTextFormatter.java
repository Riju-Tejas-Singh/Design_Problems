package loggingframework.strategies.formatter;

import loggingframework.entities.LogMessage;

public class SimpleTextFormatter implements LogFormatter {

    @Override
    public String format(LogMessage message) {
        return String.format(
                "[%s] [%s] %s",
                message.getTimestamp(),
                message.getLevel(),
                message.getMessage()
        );
    }
}
