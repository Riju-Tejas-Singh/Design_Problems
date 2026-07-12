package vendingmachine.state;

import vendingmachine.VendingMachine;
import vendingmachine.enums.Coin;

public class ItemSelectedState extends VendingMachineState {
    public ItemSelectedState(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void selectItem(String code) {
        System.out.println("Item already selected.");
    }

    /**
     * Keep inserting coin until money is sufficient
     */
    @Override
    public void insertCoin(Coin coin) {
        machine.addBalance(coin.getValue());
        System.out.println("Coin Inserted: " + coin.getValue());
        int price = machine.getInventory().getItem(machine.getSelectedItemCode()).getPrice();
        if (price <= machine.getBalance()) {
            System.out.println("Sufficient amount received");
            machine.setCurrentState(new HasMoneyState(machine));
        }
    }

    @Override
    public void dispense() {
        System.out.println("Select item first before dispense.");
    }

    @Override
    public void refund() {
        System.out.println("No money to refund");
    }
}
