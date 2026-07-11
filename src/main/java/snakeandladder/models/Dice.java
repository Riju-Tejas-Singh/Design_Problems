package snakeandladder.models;

public class Dice {
    int min;
    int max;

    public Dice(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int roll() {
        // Math.random() -> double between 0.0 to 1.0
        return (int) (Math.random() * (max - min + 1) + min); // 1 to 6
    }
}
