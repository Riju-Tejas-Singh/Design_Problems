package vendingmachine.state;

import vendingmachine.VendingMachine;
import vendingmachine.enums.Coin;

/**
 * IdleState --selectItem--> ItemSelectedState --insertCoin--> HasMoneyState --refund--> IdleState
 * HasMoneyState --dispense--> DispensingState --> IdleState
 */
public abstract class VendingMachineState {
    VendingMachine machine;

    VendingMachineState(VendingMachine machine) {
        this.machine = machine;
    }

    public abstract void selectItem(String code);
    public abstract void insertCoin(Coin coin);
    public abstract void dispense();
    public abstract void refund();
}
