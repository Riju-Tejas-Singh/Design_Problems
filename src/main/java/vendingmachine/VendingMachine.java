package vendingmachine;

import vendingmachine.entity.Inventory;
import vendingmachine.entity.Item;
import vendingmachine.enums.Coin;
import vendingmachine.state.IdleState;
import vendingmachine.state.VendingMachineState;

public class VendingMachine {
    private static VendingMachine instance;
    private final Inventory inventory = new Inventory();
    private VendingMachineState currentState;
    private int balance;
    private String selectedItemCode;

    // calling all 4 state methods
    public void selectItem(String code) {
        currentState.selectItem(code);
    }

    public void insertCoin(Coin coin) {
        currentState.insertCoin(coin);
    }

    public void dispense() {
        currentState.dispense();
    }

    public void refund() {
        currentState.refund();
    }

    // constructor and get instance methods
    private VendingMachine() {
        this.currentState = new IdleState(this);
    }

    public static VendingMachine getInstance() {
        if (instance == null) {
            synchronized (VendingMachine.class) {
                if (instance == null) {
                    instance = new VendingMachine();
                }
            }
        }
        return instance;
    }

    // getters and setters
    public void setCurrentState(VendingMachineState currentState) {
        this.currentState = currentState;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getBalance() {
        return balance;
    }

    public String getSelectedItemCode() {
        return selectedItemCode;
    }

    public void setSelectedItemCode(String selectedItemCode) {
        this.selectedItemCode = selectedItemCode;
    }

    // other methods -------------------------------

    public void addItem(String code, String name, int price, int quantity) {
        Item item = new Item(name, price);
        inventory.addItem(code, item, quantity);
    }

    public void addBalance(int amount) {
        balance += amount;
    }

    public void dispenseItem() {
        int price = inventory.getItem(selectedItemCode).getPrice();
        inventory.reduceQuantity(selectedItemCode);
        balance -= price;
        System.out.println("Item " + selectedItemCode + " has been dispensed.");
        if (balance > 0) {
            System.out.println("Returning change: " + balance);
        }
        reset();
    }

    public void reset() {
        this.balance = 0;
        this.selectedItemCode = null;
        this.currentState = new IdleState(this);
    }

    public void refundBalance() {
        if (this.balance > 0) {
            System.out.println("Returning change: " + balance);
            reset();
        }
    }
}
