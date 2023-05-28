package org.example.entities.nonmovable;

import org.example.Pair;

public class Door implements Nonmovable {
    private final Pair<Integer, Integer> position;
    public boolean visited;

    public Door(Pair<Integer, Integer> position, boolean visited) {
        this.position = position;
        this.visited = visited;
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return position;
    }
}
