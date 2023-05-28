package org.example.entities.nonmovable;

import org.example.Pair;

public class Item implements Nonmovable {
    private final Pair<Integer, Integer> position;
    private final String name;
    private final int boostAttack;
    private final int boostHealth;

    public Item(Pair<Integer, Integer> position, String name, int boostAttack, int boostHealth) {
        this.position = position;
        this.name = name;
        this.boostAttack = boostAttack;
        this.boostHealth = boostHealth;
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return position;
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
