package atm;

import atm.chainofresponsibility.CashDispenser;
import atm.chainofresponsibility.CashDispenser100;
import atm.chainofresponsibility.CashDispenser20;
import atm.chainofresponsibility.CashDispenser50;
import atm.entities.BankService;
import atm.entities.Card;
import atm.enums.OperationType;
import atm.state.ATMState;
import atm.state.IdleState;

public class ATMSystem {
    private final BankService bankService;
    private final CashDispenser cashDispenser;
    private ATMState currentState;
    private Card currentCard;

    private ATMSystem() {
        this.bankService = new BankService();
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
        double balance = bankService.getBalance(currentCard);
        System.out.printf("Your current account balance is: $%.2f%n", balance);
    }

    public void withdrawCash(int amount) {
        if (!cashDispenser.canDispenseCash(amount)) {
            throw new IllegalStateException("Insufficient cash available in the ATM.");
        }

        // money deducted from account
        bankService.withdrawMoney(currentCard, amount);
        // notes deducted from atm
        cashDispenser.dispenseCash(amount);
    }

    public void depositCash(int amount) {
        bankService.depositMoney(currentCard, amount);
    }

    // other get operations
    public Card getCardFromNumber(String cardNumber) {
        return bankService.getCardFromNumber(cardNumber);
    }

    public boolean authenticate(String pin) {
        return bankService.authenticate(currentCard, pin);
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public BankService getBankService() {
        return bankService;
    }

    // setters
    public void setState(ATMState state) {
        this.currentState = state;
    }

    public void setCurrentCard(Card card) {
        this.currentCard = card;
    }
}
