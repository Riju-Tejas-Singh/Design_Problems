package atm;

import atm.chainofresponsibility.CashDispenser;
import atm.chainofresponsibility.CashDispenser100;
import atm.chainofresponsibility.CashDispenser20;
import atm.chainofresponsibility.CashDispenser50;
import atm.entities.Account;
import atm.entities.Card;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import atm.enums.OperationType;
import atm.state.ATMState;
import atm.state.IdleState;

public class ATMSystem {
    // both hashmaps act as DBs
    private final Map<String, Card> cards = new ConcurrentHashMap<>();
    private final Map<Card, Account> cardAccountMap = new ConcurrentHashMap<>();
    // fields
    private final CashDispenser cashDispenser;
    private ATMState currentState;
    private Card currentCard;

    private ATMSystem() {
        // Create sample accounts and cards (inlined from former BankService)
        Account account1 = new Account("1234567890", 1000.0);
        Card card1 = new Card("1234-5678-9012-3456", "1234");

        Account account2 = new Account("9876543210", 500.0);
        Card card2 = new Card("9876-5432-1098-7654", "4321");

        cards.put(card1.getCardNumber(), card1);
        cards.put(card2.getCardNumber(), card2);
        cardAccountMap.put(card1, account1);
        cardAccountMap.put(card2, account2);

        CashDispenser c1 = new CashDispenser100(10);
        CashDispenser c2 = new CashDispenser50(20);
        CashDispenser c3 = new CashDispenser20(30);
        c1.setNextC(c2);
        c2.setNextC(c3);
        this.cashDispenser = c1;
        this.currentState = new IdleState();
        this.currentCard = null;
    }

    private static class Holder {
        private static final ATMSystem INSTANCE = new ATMSystem();
    }

    public static ATMSystem getInstance() {
        return Holder.INSTANCE;
    }

    // state operations on atm
    public void insertCard(String cardNumber) {
        currentState.insertCard(this, cardNumber);
    }

    public void enterPin(String pin) {
        currentState.enterPin(this, pin);
    }

    public void selectOperation(OperationType op, int amount) {
        currentState.selectOperation(this, op, amount);
    }


    // after going to operation state
    public void checkBalance() {
        double balance = getBalance(currentCard);
        System.out.printf("Your current account balance is: $%.2f%n", balance);
    }

    public void withdrawCash(int amount) {
        if (!cashDispenser.canDispenseCash(amount)) {
            throw new IllegalStateException("Insufficient cash available in the ATM.");
        }

        // money deducted from account
        cardAccountMap.get(currentCard).withdraw(amount);
        // notes deducted from atm
        cashDispenser.dispenseCash(amount);
    }

    public void depositCash(int amount) {
        cardAccountMap.get(currentCard).deposit(amount);
    }

    // other atm operations
    public boolean authenticate(String pin) {
        return currentCard.getPin().equals(pin);
    }

    public double getBalance(Card card) {
        return cardAccountMap.get(card).getBalance();
    }

    // getters
    public Card getCardFromNumber(String cardNumber) {
        return cards.getOrDefault(cardNumber, null);
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    // setters
    public void setState(ATMState state) {
        this.currentState = state;
    }

    public void setCurrentCard(Card card) {
        this.currentCard = card;
    }
}
