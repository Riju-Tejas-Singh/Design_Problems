package snakeandladder.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final int size;
    private final Map<Integer, Integer> snakesAndLadders;
    public Board(int size, List<BoardEntity> entities) {
        this.size = size;
        this.snakesAndLadders = new HashMap<>();
        entities.forEach(entity -> this.snakesAndLadders.put(entity.getStart(), entity.getEnd()));
    }
    // returns value if key is present in map, else same position
    public int getFinalPosition(int position) {
        return snakesAndLadders.getOrDefault(position, position);
    }

    public int getSize() {
        return size;
    }
}
