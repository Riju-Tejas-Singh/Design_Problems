package atm.entities;

public class Card {
    private final String cardNumber;
    private final String pin;
    private Account account;

    public Card(String cardNumber, String pin) {
        this.cardNumber = cardNumber;
        this.pin = pin;
    }

    public Card(String cardNumber, String pin, Account account) {
        this(cardNumber, pin);
        this.account = account;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
