package org.example.entities.movable;

import org.example.Pair;

public class Player extends Movable {
    private Integer health = 10;

    public Player(Pair<Integer, Integer> position) {
        super(position);
    }

    public Integer getHealth() {
        return health;
    }

    public void takeDamage(Integer damage) {
        this.health -= damage;
    }
}
