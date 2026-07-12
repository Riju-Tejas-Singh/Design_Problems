package vendingmachine;

import vendingmachine.enums.Coin;

public class Main {
    public static void main(String[] args) {
        VendingMachine vendingMachine = VendingMachine.getInstance();

        // Add products to the inventory
        vendingMachine.addItem("A1", "Coke", 25, 3);
        vendingMachine.addItem("A2", "Pepsi", 25, 2);
        vendingMachine.addItem("B1", "Water", 10, 5);

        // Select a product
        System.out.println("\n--- Step 1: Select an item ---");
        vendingMachine.selectItem("A1");

        // Insert coins
        System.out.println("\n--- Step 2: Insert coins ---");
        vendingMachine.insertCoin(Coin.DIME); // 10
        vendingMachine.insertCoin(Coin.DIME); // 10
        vendingMachine.insertCoin(Coin.NICKEL); // 5

        // Dispense the product
        System.out.println("\n--- Step 3: Dispense item ---");
        vendingMachine.dispense(); // Should dispense Coke

        // Select another item
        System.out.println("\n--- Step 4: Select another item ---");
        vendingMachine.selectItem("B1");

        // Insert more amount
        System.out.println("\n--- Step 5: Insert more than needed ---");
        vendingMachine.insertCoin(Coin.QUARTER); // 25

        // Try to dispense the product
        System.out.println("\n--- Step 6: Dispense and return change ---");
        vendingMachine.dispense();
    }

    //    --- Step 1: Select an item ---
    //    Item selected: A1
    //
    //--- Step 2: Insert coins ---
    //    Coin Inserted: 10
    //    Coin Inserted: 10
    //    Coin Inserted: 5
    //    Sufficient amount received
    //
    //--- Step 3: Dispense item ---
    //    Item A1 has been dispensed.
    //
    //--- Step 4: Select another item ---
    //    Item selected: B1
    //
    //--- Step 5: Insert more than needed ---
    //    Coin Inserted: 25
    //    Sufficient amount received
    //
    //--- Step 6: Dispense and return change ---
    //    Item B1 has been dispensed.
    //    Returning change: 15

}
