package org.example;

import org.example.entities.nonmovable.Item;

public class Equipment {
    private static final int EQUIPMENT_SIZE = 10;
    private Item[] items;

    public Equipment() {
        items = new Item[EQUIPMENT_SIZE];
    }
}
