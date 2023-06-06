package org.example;

import org.example.entities.Entity;
import org.example.entities.nonmovable.Door;
import org.example.entities.nonmovable.Hatch;
import org.example.entities.nonmovable.Wall;
import org.example.mobs.Mob;

import java.util.List;
import java.util.Map;

public class Room {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 19;

    private void putEntity(Map<Pair<Integer, Integer>, Entity> field, Entity entity) {
        field.put(entity.getPosition(), entity);
    }

    public Room(Map<Pair<Integer, Integer>, Entity> field, List<Mob> enemies, int xOffset, int yOffset, int itemsCount, int trapsCount, boolean isLast, int levelNumber, Level level) {
        {
            putEntity(field, new Wall(new Pair<>(xOffset, yOffset + getHeight() / 2 - 1)));
            putEntity(field, new Wall(new Pair<>(xOffset, yOffset + getHeight() / 2 + 1)));
        }
        for (int i = 0; i < getWidth() - 1; ++i) {
            putEntity(field, new Wall(new Pair<>(xOffset + i + 1, yOffset)));
            putEntity(field, new Wall(new Pair<>(xOffset + i + 1, yOffset + getHeight() - 1)));
        }
        for (int i = 0; i < getHeight(); ++i) {
            putEntity(field, new Wall(new Pair<>(xOffset + 1, yOffset + i)));
            putEntity(field, new Wall(new Pair<>(xOffset + getWidth() - 1, yOffset + i)));
        }
        {
            putEntity(field, new Door(new Pair<>(xOffset + 1, yOffset + getHeight() / 2), true));
            putEntity(field, new Door(new Pair<>(xOffset + getWidth() - 1, yOffset + getHeight() / 2), false));
        }
        RoomGen roomGen = new RoomGen(xOffset + 2, yOffset + 1, getWidth() - 3, getHeight() - 2);
        for (Entity entity : roomGen.getItems(itemsCount, levelNumber)) {
            putEntity(field, entity);
        }
        for (Entity entity : roomGen.getTraps(trapsCount, levelNumber)) {
            putEntity(field, entity);
        }
        for (Mob mob : roomGen.getEnemies(levelNumber, level)) {
            putEntity(field, mob);
            enemies.add(mob);
        }
        if (isLast) {
            putEntity(field, new Hatch(new Pair<>(xOffset + getWidth() - 1, getHeight() / 2)));
        }
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }
}
