package org.example;


import org.example.entities.Entity;
import org.example.entities.movable.Direction;
import org.example.entities.movable.Player;
import org.example.entities.nonmovable.*;
import org.example.render.Render;

import java.util.*;


public class LevelCtx {
    private static final int WIDTH = 19;
    private static final int HEIGHT = 17;
    private final Door door;
    private final Map<Pair<Integer, Integer>, Entity> field;
    private final Player player;
    private int roomNumber = 1;
    private List<Room> rooms;

    LevelCtx(Door door) {
        this.player = new Player(new Pair<>(1, 1));
        this.door = door;
        this.field = new HashMap<>();
        this.rooms = new LinkedList<>();
        rooms.add(new Room(0, 0, 2, 2, false));
    }

    public Entity[] getField() {
        return field.values().toArray(new Entity[0]);
    }

    public class Room {
        Room(int xoffset, int yoffset, int trapCount, int artifactsCount, boolean isBoss) {
            RoomGen roomGen = new RoomGen(xoffset, yoffset, WIDTH, HEIGHT);
            List<Entity> items = roomGen.artifactSpots(artifactsCount);
            List<Entity> traps = roomGen.traps(trapCount);
            for (int i = 0; i < WIDTH; ++i) {
                Pair<Integer, Integer> coords1 = new Pair<>(xoffset + i, yoffset);
                Pair<Integer, Integer> coords2 = new Pair<>(xoffset + i, yoffset + HEIGHT - 1);
                field.put(coords1, new Wall(coords1));
                field.put(coords2, new Wall(coords2));
            }
            for (int i = 0; i < HEIGHT; ++i) {
                Pair<Integer, Integer> coords1 = new Pair<>(xoffset, yoffset + i);
                Pair<Integer, Integer> coords2 = new Pair<>(xoffset + WIDTH - 1, yoffset + i);
                if (i == 8) {
                    if (xoffset != 0) {
                        field.put(coords1, new Door(coords1, false));
                    } else {
                        field.put(coords1, new Wall(coords1));
                    }
                    if (!isBoss) {
                        field.put(coords2, new Door(coords2, true));
                    } else {
                        field.put(coords2, new Wall(coords2));
                    }
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

            if (isBoss) {
                Pair<Integer, Integer> hatchCoords = new Pair<>(xoffset + WIDTH - 2, 8);
                field.put(hatchCoords, new Hatch(hatchCoords));
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

    public boolean move(Direction direction) {
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
        /*player.setPosition(
                new Pair<>(
                        clamp(player.getPosition().getFirst() + dx, 1, WIDTH - 2),
                        clamp(player.getPosition().getSecond() + dy, 1, HEIGHT - 2)
                )
        );*/
        Pair<Integer, Integer> newPos = new Pair<>(player.getPosition().getFirst() + dx,
                player.getPosition().getSecond() + dy);
        Entity entity = field.get(newPos);
        if (entity != null) {
            if (entity instanceof Item) {
                field.remove(newPos);
                this.player.setPosition(newPos);
            } else if (entity instanceof Trap) {
                this.player.setPosition(newPos);
                player.takeDamage(((Trap) entity).getDamage());
                field.remove(newPos);
            } else if (entity instanceof Wall) {
                //Do nothing
            } else if (entity instanceof Door) {
                Door casted = (Door) entity;
                this.player.setPosition(newPos);
                if (casted.visited && roomNumber <= 3) {
                    casted.visited = false;
                    Pair<Integer, Integer> coords1 = new Pair<>(WIDTH * roomNumber + (roomNumber - 1), 7),
                            coords2 = new Pair<>(WIDTH * roomNumber + (roomNumber - 1), 9);
                    field.put(coords1, new Wall(coords1));
                    field.put(coords2, new Wall(coords2));
                    if (roomNumber == 3) {
                        rooms.add(new Room(roomNumber * (WIDTH + 1), 0, 0, 0, true));
                    } else {
                        rooms.add(new Room(roomNumber * (WIDTH + 1), 0, 2, 2, false));
                    }
                    roomNumber++;
                }
            } else if (entity instanceof Hatch) {
                this.player.setPosition(newPos);
                if (((Hatch) entity).isAvailable) {
                    return true;
                }
            }
        } else {
            this.player.setPosition(newPos);
        }
        return false;
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(value, max));
    }
}
