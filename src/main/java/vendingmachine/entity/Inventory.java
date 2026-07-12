package vendingmachine.entity;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Map<String, Item> itemMap = new HashMap<>();
    private final Map<String, Integer> itemQuantityMap = new HashMap<>();

    public void addItem(String code, Item item, int quantity) {
        itemMap.put(code, item);
        itemQuantityMap.put(code, quantity);
    }

    public Item getItem(String code) {
        return itemMap.get(code);
    }

    public boolean isAvailable(String code) {
        return itemQuantityMap.getOrDefault(code, 0) > 0;
    }

    public void reduceQuantity(String code) {
        int quantity = itemQuantityMap.getOrDefault(code, 0);
        itemQuantityMap.put(code, quantity - 1);
    }
}
