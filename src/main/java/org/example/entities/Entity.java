package org.example.entities;

import org.example.Pair;

public interface Entity {
    char getFieldSymbol();

    Pair<Integer, Integer> getPosition();
}
