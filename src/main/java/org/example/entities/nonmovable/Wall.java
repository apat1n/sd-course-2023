package org.example.entities.nonmovable;

import org.example.Pair;

public class Wall implements Nonmovable {
    private final Pair<Integer, Integer> position;

    public Wall(Pair<Integer, Integer> position) {
        this.position = position;
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return position;
    }
}
