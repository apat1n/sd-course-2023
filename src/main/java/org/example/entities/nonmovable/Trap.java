package org.example.entities.nonmovable;

import org.example.Pair;

public class Trap implements Nonmovable {
    private final Pair<Integer, Integer> position;
    private final int damage;

    public Trap(Pair<Integer, Integer> position, int damage) {
        this.position = position;
        this.damage = damage;
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    public int getDamage() {
        return damage;
    }
}
