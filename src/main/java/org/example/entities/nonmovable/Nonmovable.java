package org.example.entities.nonmovable;

import org.example.Pair;
import org.example.entities.Entity;

public abstract class Nonmovable implements Entity {
    private final Pair<Integer, Integer> position;

    public Nonmovable(Pair<Integer, Integer> position) {
        this.position = position;
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return position;
    }
}
