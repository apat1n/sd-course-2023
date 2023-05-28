package org.example;


import org.example.entities.Entity;
import org.example.entities.movable.Direction;
import org.example.entities.movable.Player;
import org.example.entities.nonmovable.Item;
import org.example.entities.nonmovable.Trap;
import org.example.entities.nonmovable.Door;
import org.example.entities.nonmovable.Wall;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LevelCtx {
    private static final int WIDTH = 17;
    private static final int HEIGHT = 17;
    private final Door door;
    private final Map<Pair<Integer, Integer>, Entity> field;
    private final Player player;
    private Room room;

    LevelCtx(List<Entity> items, List<Entity> traps, Door door) {
        this.player = new Player(new Pair<>(1, 1));
        this.door = door;
        this.field = new HashMap<>();
        this.room = new Room(0, 0, items, traps);
    }

    public Entity[] getField() {
        return field.values().toArray(new Entity[0]);
    }

    public class Room {
        Room(int xoffset, int yoffset, List<Entity> items, List<Entity> traps) {
            for (int i = 0; i < WIDTH; ++i) {
                Pair<Integer, Integer> coords1 = new Pair<>(xoffset + i, yoffset);
                Pair<Integer, Integer> coords2 = new Pair<>(xoffset + i, yoffset + HEIGHT - 1);
                if (i == 7) {
                    field.put(coords1, new Door(coords1));
                    field.put(coords2, new Door(coords2));
                } else {
                    field.put(coords1, new Wall(coords1));
                    field.put(coords2, new Wall(coords2));
                }
            }
            for (int i = 0; i < HEIGHT; ++i) {
                Pair<Integer, Integer> coords1 = new Pair<>(xoffset, yoffset + i);
                Pair<Integer, Integer> coords2 = new Pair<>(xoffset + WIDTH - 1, yoffset + i);
                if (i == 3) {
                    field.put(coords1, new Door(coords1));
                    field.put(coords2, new Door(coords2));
                } else {
                    field.put(coords1, new Wall(coords1));
                    field.put(coords2, new Wall(coords2));
                }
            }
            for (Entity entity : items) {
                field.put(entity.getPosition(), entity);
            }
            for (Entity entity : traps) {
                field.put(entity.getPosition(), entity);
            }
        }
    }

    public static int getWidth() {
        return WIDTH;
    }

    public static int getHeight() {
        return HEIGHT;
    }

    public Player getPlayer() {
        return player;
    }

    public void move(Direction direction) {
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
        player.setPosition(
                new Pair<>(
                        clamp(player.getPosition().getFirst() + dx, 1, WIDTH - 2),
                        clamp(player.getPosition().getSecond() + dy, 1, HEIGHT - 2)
                )
        );
        Entity entity = field.get(player.getPosition());
        if (entity != null) {
            if (entity instanceof Item) {
                field.remove(player.getPosition());
            } else if (entity instanceof Trap) {
                player.takeDamage(((Trap) entity).getDamage());
                field.remove(player.getPosition());
            }
        }
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(value, max));
    }
}
