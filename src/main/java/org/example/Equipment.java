package org.example;

import org.example.entities.nonmovable.Loot;

public class Equipment {
    private static final int EQUIPMENT_SIZE = 10;
    private final Loot[] loots;

    public Equipment() {
        this.loots = new Loot[EQUIPMENT_SIZE];
    }

    public int size() {
        return EQUIPMENT_SIZE;
    }

    public boolean add(Loot loot) {
        for (int i = 0; i < size(); ++i) {
            if (loots[i] == null) {
                loots[i] = loot;
                return true;
            }
        }
        return false;
    }

    public Loot get(int index) {
        return loots[index];
    }
}
