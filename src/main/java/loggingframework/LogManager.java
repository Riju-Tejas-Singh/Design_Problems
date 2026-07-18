package loggingframework;

import loggingframework.enums.LogLevel;

import java.util.HashMap;
import java.util.Map;

public class LogManager {
    private final Map<String, Logger> logs;
    private LogManager() {
        logs = new HashMap<>();
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
