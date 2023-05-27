package org.example.entities.movable;

import org.example.Pair;

public class Player extends Movable {
    public static final char fieldSymbol = '@';

    public Player(Pair<Integer, Integer> position) {
        super(position);
    }

    @Override
    public char getFieldSymbol() {
        return fieldSymbol;
    }
}
