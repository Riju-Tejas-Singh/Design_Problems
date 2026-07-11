package snakeandladder.models;

public class Ladder extends BoardEntity{
    public Ladder(int start, int end) {
        super(start, end);
        if (start >= end) {
            throw new IllegalArgumentException("Ladder base must be at a lower position than its top.");
        }
    }
}
