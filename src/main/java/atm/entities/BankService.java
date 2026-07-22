package atm.entities;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BankService {
    private final Map<String, Card> cards = new ConcurrentHashMap<>();
    private final Map<Card, Account> cardAccountMap = new ConcurrentHashMap<>();

    public BankService() {
        // Create sample accounts and cards
        Account account1 = new Account("1234567890", 1000.0);
        Card card1 = new Card("1234-5678-9012-3456", "1234", account1);

        Account account2 = new Account("9876543210", 500.0);
        Card card2 = new Card("9876-5432-1098-7654", "4321", account2);
        cards.put(card1.getCardNumber(), card1);
        cards.put(card2.getCardNumber(), card2);
        cardAccountMap.put(card1, account1);
        cardAccountMap.put(card2, account2);
    }

    public boolean authenticate(Card card, String pin) {
        return card.getPin().equals(pin);
    }

    public Card getCardFromNumber(String cardNumber) {
        return cards.getOrDefault(cardNumber, null);
    }

    // user operations performed on account
    public double getBalance(Card card) {
        return cardAccountMap.get(card).getBalance();
    }

    public void withdrawMoney(Card card, double amount) {
        cardAccountMap.get(card).withdraw(amount);
    }

    public void depositMoney(Card card, double amount) {
        cardAccountMap.get(card).deposit(amount);
    }

}
