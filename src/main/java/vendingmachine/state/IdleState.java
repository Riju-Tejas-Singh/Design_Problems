package vendingmachine.state;

import vendingmachine.VendingMachine;
import vendingmachine.enums.Coin;

public class IdleState extends  VendingMachineState {
    public IdleState(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void selectItem(String code) {
        if (!machine.getInventory().isAvailable(code)) {
            System.out.println("Item not available");
            return;
        }
        machine.setSelectedItemCode(code);
        machine.setCurrentState(new ItemSelectedState(machine));
        System.out.println("Item selected: " + code);
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Please select item first before inserting coin");
    }

    @Override
    public void dispense() {
        System.out.println("No Item selected");
    }

    @Override
    public void refund() {
        System.out.println("No money to refund");
    }
}
