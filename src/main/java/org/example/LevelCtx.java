package org.example;


import org.example.entities.Entity;
import org.example.entities.movable.Movable;
import org.example.entities.movable.Player;
import org.example.entities.nonmovable.Item;
import org.example.entities.nonmovable.Trap;
import org.example.entities.nonmovable.Door;
import org.example.entities.nonmovable.Wall;
import org.example.render.Render;

import java.util.*;

public class LevelCtx {
    public final int WIDTH = 17;
    public final int HEIGHT = 17;
    private final Door door;
    private final Map<Pair<Integer, Integer>, Entity> field;
    private Player player;
    private int currentRoomX = 0, currentRoomY = 0;
    private Render render;
    private Room room;

    public void setRender(Render render) {
        this.render = render;
    }

    public Entity[] getField() {
        return field.values().toArray(new Entity[0]);
    }

    public class Room {
        Room(int xoffset, int yoffset) {
            RoomGen roomGen = new RoomGen(0, 0, HEIGHT, WIDTH, 2, 0, 2);
            HashMap<Pair<Integer, Integer>, Trap> traps = (HashMap<Pair<Integer, Integer>, Trap>) roomGen.traps();
            HashMap<Pair<Integer, Integer>, Item> items = (HashMap<Pair<Integer, Integer>, Item>) roomGen.artifactSpots();

            for (int i = 0; i < WIDTH; ++i) {
                Pair<Integer, Integer> coords1 = new Pair<>(xoffset + i, yoffset),
                        coords2 = new Pair<>(xoffset + i, yoffset + HEIGHT - 1);
                if (i == 7) {
                    field.put(coords1, new Door(coords1));
                    field.put(coords2, new Door(coords2));
                    continue;
                }
                field.put(coords1, new Wall(coords1));
                field.put(coords2, new Wall(coords2));
            }
            for (int i = 0; i < HEIGHT; ++i) {
                Pair<Integer, Integer> coords1 = new Pair<>(xoffset, yoffset + i),
                        coords2 = new Pair<>(xoffset + WIDTH - 1, yoffset + i);
                if (i == 3) {
                    field.put(coords1, new Door(coords1));
                    field.put(coords2, new Door(coords2));
                    continue;
                }
                field.put(coords1, new Wall(coords1));
                field.put(coords2, new Wall(coords2));
            }
            field.putAll(traps);
            field.putAll(items);
        }
    }

    LevelCtx(Door door) {
        this.player = new Player(new Pair<>(1, 1));
        this.door = door;
        this.field = new HashMap<>();
        this.room = new Room(0, 0);
    }

    public Player getPlayer() {
        return player;
    }

    public void move(Movable.Direction direction) {
        Pair<Integer, Integer> pos = this.player.getPosition();
        Pair<Integer, Integer> newPos = new Pair<>(pos.getFirst(), pos.getSecond());
        switch (direction) {
            case LEFT:
                newPos.setFirst(newPos.getFirst() - 1);
                pos.setFirst(Math.max(pos.getFirst() - 1, 1));
                break;
            case FORWARD:
                newPos.setSecond(newPos.getSecond() - 1);
                pos.setSecond(Math.max(pos.getSecond() - 1, 1));
                break;
            case BACKWARD:
                newPos.setSecond(newPos.getSecond() + 1);
                pos.setSecond(Math.min(pos.getSecond() + 1, HEIGHT - 2));
                break;
            case RIGHT:
                newPos.setFirst(newPos.getFirst() - 1);
                pos.setFirst(Math.min(pos.getFirst() + 1, WIDTH - 2));
                break;
        }

        Entity entity = field.get(pos);
        if (entity != null) {
            if (entity instanceof Item) {
                field.remove(pos);
                render.renderField();
            } else if (entity instanceof Trap) {
                player.takeDamage(((Trap) entity).getDamage());
                field.remove(pos);
                render.renderStatus();
            }
        }
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(value, max));
    }
}
