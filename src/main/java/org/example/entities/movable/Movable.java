package org.example.entities.movable;

import net.slashie.libjcsi.CharKey;
import org.example.LevelCtx;
import org.example.Pair;
import org.example.entities.Entity;

public abstract class Movable implements Entity {
    private Pair<Integer, Integer> position;

    public enum Direction {
        LEFT, FORWARD, BACKWARD, RIGHT
    }

    public Movable(Pair<Integer, Integer> position) {
        this.position = position;
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    public void move(Direction direction) {
        switch (direction) {
            case LEFT:
                position.setFirst(position.getFirst() - 1);
                break;
            case FORWARD:
                position.setSecond(position.getSecond() - 1);
                break;
            case BACKWARD:
                position.setSecond(position.getSecond() + 1);
                break;
            case RIGHT:
                position.setFirst(position.getFirst() + 1);
                break;
        }
    }
}
