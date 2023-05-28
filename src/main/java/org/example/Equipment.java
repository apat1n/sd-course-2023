package org.example;

import org.example.entities.nonmovable.Item;

public class Equipment {
    private static final int EQUIPMENT_SIZE = 10;
    private final Item[] items;

    public Equipment() {
        this.items = new Item[EQUIPMENT_SIZE];
    }

    public int size() {
        return EQUIPMENT_SIZE;
    }

    public boolean add(Item item) {
        for (int i = 0; i < size(); ++i) {
            if (items[i] == null) {
                items[i] = item;
                return true;
            }
        }
        return false;
    }

    public Item get(int index) {
        return items[index];
    }
}
