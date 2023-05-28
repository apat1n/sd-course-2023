package org.example.entities.nonmovable;

import org.example.Pair;

public class Hatch implements Nonmovable {
    private final Pair<Integer, Integer> position;
    private boolean isAvailable = false;

    public Hatch(Pair<Integer, Integer> position) {
        this.position = position;
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void isAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
