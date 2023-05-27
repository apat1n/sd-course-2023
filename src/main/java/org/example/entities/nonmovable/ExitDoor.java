package org.example.entities.nonmovable;

import org.example.Pair;
public class ExitDoor extends Nonmovable {
    private static final char fieldSymbol = '0';

    public ExitDoor(Pair<Integer, Integer> position) {
        super(position);
    }

    @Override
    public char getFieldSymbol() {
        return fieldSymbol;
    }
}
