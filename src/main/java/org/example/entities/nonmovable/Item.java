package org.example.entities.nonmovable;

import org.example.Pair;

public class Item extends Nonmovable {
    private static final char fieldSymbol = '!';
    private final String name;
    private final int boostAttack;
    private final int boostHealth;

    public Item(Pair<Integer, Integer> position, String name, int boostAttack, int boostHealth) {
        super(position);
        this.name = name;
        this.boostAttack = boostAttack;
        this.boostHealth = boostHealth;
    }

    @Override
    public char getFieldSymbol() {
        return fieldSymbol;
    }

    public String getName() {
        return name;
    }

    public int getBoostAttack() {
        return boostAttack;
    }

    public int getBoostHealth() {
        return boostHealth;
    }
}
