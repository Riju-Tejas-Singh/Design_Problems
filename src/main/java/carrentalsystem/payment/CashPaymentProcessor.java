package carrentalsystem.payment;

public class CashPaymentProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing payment through cash");
        // Process cash payment
        // ...
        return true;
    }
}
