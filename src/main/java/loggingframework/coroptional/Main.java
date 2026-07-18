package loggingframework.coroptional;

public class Main {

    public static void main(String[] args) {

        Logger logger =
                LogManager.getInstance().getLogger("PaymentService");

        logger.info("Payment Successful");
        logger.warn("Low Balance");
        logger.error("Database Down");
    }
    //    [2026-07-19T00:56:58.077823] [INFO] Payment Successful
    //    [2026-07-19T00:56:58.089195] [WARN] Low Balance
    //    [2026-07-19T00:56:58.089349] [ERROR] Database Down
}
