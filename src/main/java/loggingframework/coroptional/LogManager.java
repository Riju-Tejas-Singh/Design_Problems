package loggingframework.coroptional;

import loggingframework.coroptional.cor.AbstractLogger;
import loggingframework.coroptional.cor.ErrorLogger;
import loggingframework.coroptional.cor.InfoLogger;
import loggingframework.coroptional.cor.WarnLogger;
import loggingframework.strategies.appender.ConsoleAppender;
import loggingframework.strategies.appender.FileAppender;
import loggingframework.strategies.appender.LogAppender;
import loggingframework.strategies.formatter.LogFormatter;
import loggingframework.strategies.formatter.SimpleTextFormatter;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class LogManager {

    private final ConcurrentHashMap<String, Logger> loggers;

    private LogManager() {
        loggers = new ConcurrentHashMap<>();
    }

    private static class Holder {
        private static final LogManager INSTANCE = new LogManager();
    }

    public static LogManager getInstance() {
        return Holder.INSTANCE;
    }

    public Logger getLogger(String name) {

        return loggers.computeIfAbsent(name, loggerName -> {

            LogFormatter formatter = new SimpleTextFormatter();

            ConsoleAppender console = new ConsoleAppender();
            console.setFormatter(formatter);

            FileAppender file = new FileAppender("src/main/java/loggingframework/application.log");
            file.setFormatter(formatter);

            List<LogAppender> appenders = List.of(console, file);

            AbstractLogger info = new InfoLogger(appenders);
            AbstractLogger warn = new WarnLogger(appenders);
            AbstractLogger error = new ErrorLogger(appenders);

            info.setNextLogger(warn);
            warn.setNextLogger(error);

            return new Logger(loggerName, info);
        });
    }
}