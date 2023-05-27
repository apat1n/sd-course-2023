package org.example.entities.nonmovable;

import org.example.Pair;

public class Wall extends Nonmovable {
    private static final char fieldSymbol = '#';

    public Wall(Pair<Integer, Integer> position) {
        super(position);
    }

    @Override
    public char getFieldSymbol() {
        return fieldSymbol;
    }
}
