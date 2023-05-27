package org.example.entities.nonmovable;

import org.example.Pair;
import org.example.entities.Entity;

public class Trap extends Nonmovable {
    private static final char fieldSymbol = '^';
    private final int damage;

    public Trap(Pair<Integer, Integer> position, int damage) {
        super(position);
        this.damage = damage;
    }

    @Override
    public char getFieldSymbol() {
        return fieldSymbol;
    }

    public int getDamage() {
        return damage;
    }
}
