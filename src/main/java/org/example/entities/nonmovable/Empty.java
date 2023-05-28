package org.example.entities.nonmovable;

import org.example.Pair;

public class Empty implements Nonmovable {
    private final Pair<Integer, Integer> position;

    public Empty(Pair<Integer, Integer> position) {
        this.position = position;
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return position;
    }
}
