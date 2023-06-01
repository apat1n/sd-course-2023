package org.example.entities.nonmovable;

import org.example.Pair;

public class Boost implements Item {
    private final Pair<Integer, Integer> position;
    private final String name;
    private final int boostAttack;
    private final int boostHealth;

    public Boost(Pair<Integer, Integer> position, String name, int boostAttack, int boostHealth) {
        this.position = position;
        this.name = name;
        this.boostAttack = boostAttack;
        this.boostHealth = boostHealth;
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getBoostAttack() {
        return boostAttack;
    }

    @Override
    public int getBoostHealth() {
        return boostHealth;
    }
}
