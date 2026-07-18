package loggingframework;

import loggingframework.enums.LogLevel;
import loggingframework.strategies.appender.ConsoleAppender;
import loggingframework.strategies.appender.FileAppender;
import loggingframework.strategies.formatter.LogFormatter;
import loggingframework.strategies.formatter.SimpleTextFormatter;

public class Main {

    public static void main(String[] args) {

        LogManager logManager = LogManager.getInstance();

        // private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
        Logger logger = logManager.getLogger("PaymentService");
        // set severity of log level wrt that logger of PaymentService
        logger.setLevel(LogLevel.DEBUG);

        LogFormatter formatter = new SimpleTextFormatter();

        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setFormatter(formatter);

        FileAppender fileAppender =
                new FileAppender("src/main/java/loggingframework/application.log");
        fileAppender.setFormatter(formatter);

        logger.addAppender(consoleAppender);
        logger.addAppender(fileAppender);

        logger.debug("Debug Message");
        logger.info("Payment Successful");
        logger.warn("Low Balance");
        logger.error("Database Down");
        logger.fatal("Application Crashed");
    }
    //      [2026-07-18T13:14:21.937664] [DEBUG] Debug Message
    //      [2026-07-18T13:14:21.948127] [INFO] Payment Successful
    //      [2026-07-18T13:14:21.948267] [WARN] Low Balance
    //      [2026-07-18T13:14:21.948380] [ERROR] Database Down
    //      [2026-07-18T13:14:21.948500] [FATAL] Application Crashed
}
