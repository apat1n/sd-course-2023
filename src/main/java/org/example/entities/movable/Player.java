package org.example.entities.movable;

import org.example.Pair;

public class Player implements Movable {
    private static final int INITIAL_HEALTH = 10;
    private Pair<Integer, Integer> position;
    private int health = INITIAL_HEALTH;

    public Player(Pair<Integer, Integer> position) {
        this.position = position;
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    @Override
    public void setPosition(Pair<Integer, Integer> position) {
        this.position = position;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
    }
}
