package loggingframework;

import loggingframework.enums.LogLevel;

import java.util.concurrent.ConcurrentHashMap;

public class LogManager {
    private final ConcurrentHashMap<String, Logger> logs;
    private LogManager() {
        this.logs = new ConcurrentHashMap<>();
    }
    private static class Holder {
        private static final LogManager INSTANCE = new LogManager();
    }
    public static LogManager getInstance() {
        return Holder.INSTANCE;
    }

    public Logger getLogger(String name) {
        return logs.computeIfAbsent(name, loggerName -> new Logger(loggerName, LogLevel.INFO));
    }
}
