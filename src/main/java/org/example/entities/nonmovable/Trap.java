package org.example.entities.nonmovable;

import org.example.Pair;

public class Trap extends Nonmovable {
    private final int damage;

    public Trap(Pair<Integer, Integer> position, int damage) {
        super(position);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
