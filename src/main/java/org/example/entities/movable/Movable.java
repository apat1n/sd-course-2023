package org.example.entities.movable;

import org.example.Pair;
import org.example.entities.Entity;

public interface Movable extends Entity {
    void setPosition(Pair<Integer, Integer> position);
}
