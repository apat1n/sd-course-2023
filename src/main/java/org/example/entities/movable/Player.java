package org.example.entities.movable;

import org.example.Pair;

public class Player implements Movable {
    private Pair<Integer, Integer> position;
    private Integer health = 10;

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

    public Integer getHealth() {
        return health;
    }

    public void takeDamage(Integer damage) {
        this.health -= damage;
    }
}
