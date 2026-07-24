package carrentalsystem.payment;

public class CreditCardPaymentProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing  payment through credit card: $" + amount);
        // Process credit card payment
        // ...
        return true;
    }
}
