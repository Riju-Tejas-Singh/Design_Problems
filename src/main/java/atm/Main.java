package atm;

import atm.enums.OperationType;

public class Main {
    public static void main(String[] args) {
        ATMSystem atmSystem = ATMSystem.getInstance();

        // Perform Check Balance operation
        atmSystem.insertCard("1234-5678-9012-3456");
        atmSystem.enterPin("1234");
        atmSystem.selectOperation(OperationType.CHECK_BALANCE, 0); // $1000

        // Perform Withdraw Cash operation
        atmSystem.insertCard("1234-5678-9012-3456");
        atmSystem.enterPin("1234");
        atmSystem.selectOperation(OperationType.WITHDRAW_CASH, 570);

        // Perform Deposit Cash operation
        atmSystem.insertCard("1234-5678-9012-3456");
        atmSystem.enterPin("1234");
        atmSystem.selectOperation(OperationType.DEPOSIT_CASH, 200);

        // Perform Check Balance operation
        atmSystem.insertCard("1234-5678-9012-3456");
        atmSystem.enterPin("1234");
        atmSystem.selectOperation(OperationType.CHECK_BALANCE, 0); // $630

        // Perform Withdraw Cash more than balance
        atmSystem.insertCard("1234-5678-9012-3456");
        atmSystem.enterPin("1234");
        atmSystem.selectOperation(OperationType.WITHDRAW_CASH, 700); // Insufficient balance

        // Insert Incorrect PIN
        atmSystem.insertCard("1234-5678-9012-3456");
        atmSystem.enterPin("3425");
    }

//    Card has been inserted.
//    Authenticating PIN...
//    Authentication successful.
//    Your current account balance is: $1000.00
//    Transaction complete.
//    Ending session. Card has been ejected. Thank you for using our ATM.
//
//    Card has been inserted.
//    Authenticating PIN...
//    Authentication successful.
//    Processing withdrawal for $570
//    Dispensing 5 x $100
//    Dispensing 1 x $50
//    Dispensing 1 x $20
//    Transaction complete.
//    Ending session. Card has been ejected. Thank you for using our ATM.
//
//    Card has been inserted.
//    Authenticating PIN...
//    Authentication successful.
//    Processing deposit for $200
//    Transaction complete.
//    Ending session. Card has been ejected. Thank you for using our ATM.
//
//    Card has been inserted.
//    Authenticating PIN...
//    Authentication successful.
//    Your current account balance is: $630.00
//    Transaction complete.
//    Ending session. Card has been ejected. Thank you for using our ATM.
//
//    Card has been inserted.
//    Authenticating PIN...
//    Authentication successful.
//    Error: Insufficient balance.
//    Transaction complete.
//    Ending session. Card has been ejected. Thank you for using our ATM.
//
//    Card has been inserted.
//    Authenticating PIN...
//    Authentication failed: Incorrect PIN.
//    Card has been ejected. Thank you for using our ATM.
}
