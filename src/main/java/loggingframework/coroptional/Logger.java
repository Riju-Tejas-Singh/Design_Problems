package loggingframework.coroptional;

import loggingframework.coroptional.cor.AbstractLogger;
import loggingframework.entities.LogMessage;
import loggingframework.enums.LogLevel;

public class Logger {

    private final String name;
    private final AbstractLogger chain;

    public Logger(String name, AbstractLogger chain) {
        this.name = name;
        this.chain = chain;
    }

    public void info(String msg) {
        chain.log(new LogMessage(LogLevel.INFO, msg));
    }

    public void warn(String msg) {
        chain.log(new LogMessage(LogLevel.WARN, msg));
    }

    public void error(String msg) {
        chain.log(new LogMessage(LogLevel.ERROR, msg));
    }

    public String getName() {
        return name;
    }
}
