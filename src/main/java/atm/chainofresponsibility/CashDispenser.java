package atm.chainofresponsibility;

public abstract class CashDispenser {
    private CashDispenser nextChain;
    private final int noteValue;
    private int numNotes;

    public CashDispenser(int noteValue, int numNotes) {
        this.noteValue = noteValue;
        this.numNotes = numNotes;
    }

    public void setNextC(CashDispenser nextChain) {
        this.nextChain = nextChain;
    }

    public synchronized void dispenseCash(int amount) {

        int notes = Math.min(amount / noteValue, numNotes);

        if (notes > 0) {
            System.out.println("Dispensing " + notes + " x $" + noteValue);
            numNotes -= notes;
        }

        int remaining = amount - notes * noteValue;

        if (remaining > 0 && nextChain != null) {
            nextChain.dispenseCash(remaining);
        }
    }

    public synchronized boolean canDispenseCash(int amount) {

        if (amount < 0) return false;
        if (amount == 0) return true;

        int remaining = amount - Math.min(amount / noteValue, numNotes) * noteValue;

        return remaining == 0 ||
                (nextChain != null && nextChain.canDispenseCash(remaining));
    }
}