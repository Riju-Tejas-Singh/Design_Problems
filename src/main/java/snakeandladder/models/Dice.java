package snakeandladder.models;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    int min;
    int max;

    public Dice(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int roll() {
        return ThreadLocalRandom.current().nextInt(min,max+1);
        // Math.random() -> double between 0.0 to 1.0
        //  return (int) (Math.random() * (max - min + 1) + min); // 1 to 6
    }
}
