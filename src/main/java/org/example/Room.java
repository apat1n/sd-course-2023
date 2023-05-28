package org.example;

import org.example.entities.Entity;
import org.example.entities.nonmovable.Door;
import org.example.entities.nonmovable.Hatch;
import org.example.entities.nonmovable.Wall;

import java.util.Map;

public class Room {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 19;

    private void putEntity(Map<Pair<Integer, Integer>, Entity> field, Entity entity) {
        field.put(entity.getPosition(), entity);
    }

    public Room(Map<Pair<Integer, Integer>, Entity> field, int xOffset, int yOffset, int itemsCount, int trapsCount, boolean isLast) {
        {
            Pair<Integer, Integer> pos1 = new Pair<>(xOffset, yOffset + getHeight() / 2 - 1);
            Pair<Integer, Integer> pos2 = new Pair<>(xOffset, yOffset + getHeight() / 2 + 1);
            putEntity(field, new Wall(pos1));
            putEntity(field, new Wall(pos2));
        }
        for (int i = 0; i < getWidth() - 1; ++i) {
            Pair<Integer, Integer> pos1 = new Pair<>(xOffset + i + 1, yOffset);
            Pair<Integer, Integer> pos2 = new Pair<>(xOffset + i + 1, yOffset + getHeight() - 1);
            putEntity(field, new Wall(pos1));
            putEntity(field, new Wall(pos2));
        }
        for (int i = 0; i < getHeight(); ++i) {
            Pair<Integer, Integer> pos1 = new Pair<>(xOffset + 1, yOffset + i);
            Pair<Integer, Integer> pos2 = new Pair<>(xOffset + getWidth() - 1, yOffset + i);
            if (i == getHeight() / 2) {
                putEntity(field, new Door(pos1, true));
                putEntity(field, new Door(pos2, false));
            } else {
                putEntity(field, new Wall(pos1));
                putEntity(field, new Wall(pos2));
            }
        }
        RoomGen roomGen = new RoomGen(xOffset + 1, yOffset, getWidth() - 1, getHeight());
        for (Entity entity : roomGen.getItems(itemsCount)) {
            putEntity(field, entity);
        }
        for (Entity entity : roomGen.getTraps(trapsCount)) {
            putEntity(field, entity);
        }
        if (isLast) {
            Pair<Integer, Integer> pos = new Pair<>(xOffset + getWidth() - 1, getHeight() / 2);
            putEntity(field, new Hatch(pos));
        }
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }
}
