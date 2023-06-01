package org.example;


import org.example.entities.Entity;
import org.example.entities.movable.Direction;
import org.example.entities.movable.Player;
import org.example.entities.nonmovable.Door;
import org.example.entities.nonmovable.Hatch;
import org.example.entities.nonmovable.Item;
import org.example.entities.nonmovable.Trap;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Level {
    private final int itemsCount;
    private final int trapsCount;
    private final Map<Pair<Integer, Integer>, Entity> field;
    private final List<Room> rooms;

    public Level(int itemsCount, int trapsCount) {
        this.itemsCount = itemsCount;
        this.trapsCount = trapsCount;
        field = new HashMap<>();
        rooms = new LinkedList<>(List.of(new Room(field, 0, 0, itemsCount, trapsCount, false)));
    }

    public int getWidth() {
        return rooms.stream().map(Room::getWidth).reduce(0, Integer::sum);
    }

    public int getHeight() {
        return rooms.stream().map(Room::getHeight).max(Integer::compareTo).orElse(0);
    }

    public Map<Pair<Integer, Integer>, Entity> getField() {
        return field;
    }

    public boolean move(Player player, Direction direction) {
        int dx = 0, dy = 0;
        switch (direction) {
            case LEFT:
                dx = -1;
                break;
            case FORWARD:
                dy = -1;
                break;
            case BACKWARD:
                dy = 1;
                break;
            case RIGHT:
                dx = 1;
                break;
        }

        Pair<Integer, Integer> newPos = new Pair<>(
                player.getPosition().getFirst() + dx,
                player.getPosition().getSecond() + dy
        );
        Entity entity = field.get(newPos);
        if (entity == null) {
            if (newPos.getFirst() >= 0) {
                player.setPosition(newPos);
            }
        } else if (entity instanceof Item) {
            if (player.apply((Item) entity)) {
                field.remove(newPos);
            }
            player.setPosition(newPos);
        } else if (entity instanceof Trap) {
            player.apply((Trap) entity);
            player.setPosition(newPos);
        } else if (entity instanceof Door) {
            Door door = (Door) entity;
            player.setPosition(newPos);
            if (!door.getVisited()) {
                door.setVisited(true);
                if (rooms.size() == 3) {
                    rooms.add(new Room(field, getWidth(), 0, 0, 0, true));
                } else {
                    rooms.add(new Room(field, getWidth(), 0, itemsCount, trapsCount, false));
                }
            }
        } else if (entity instanceof Hatch) {
            ((Hatch) entity).isAvailable(true);
            return ((Hatch) entity).isAvailable();
        }
        return false;
    }
}
