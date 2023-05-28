package org.example;


import org.example.entities.Entity;
import org.example.entities.movable.Movable;
import org.example.entities.movable.Player;
import org.example.entities.nonmovable.Item;
import org.example.entities.nonmovable.Trap;
import org.example.entities.nonmovable.Door;
import org.example.entities.nonmovable.Wall;
import org.example.render.Render;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class LevelCtx {
    public final int WIDTH = 20;
    public final int HEIGHT = 20;
    private final Door door;
    private final Set<Entity> field;
    private Player player;

    private Render render;

    LevelCtx(Door door) {
        this.player = new Player(new Pair<>(1, 1));
        this.door = door;
        RoomGen roomGen = new RoomGen(0, 0, HEIGHT, WIDTH, 2, 0, 2);
        List<Trap> traps = roomGen.traps();
        List<Item> items = roomGen.artifactSpots();
        this.field = new HashSet<>();

        for (int i = 0; i < WIDTH; ++i) {
            field.add(new Wall(new Pair<>(i, 0)));
            field.add(new Wall(new Pair<>(i, HEIGHT - 1)));
        }
        for (int i = 0; i < HEIGHT; ++i) {
            field.add(new Wall(new Pair<>(0, i)));
            field.add(new Wall(new Pair<>(WIDTH - 1, i)));
        }
        field.addAll(traps);
        field.addAll(items);
    }

    public void setRender(Render render) {
        this.render = render;
    }

    public Set<Entity> getField() {
        return field;
    }

    public Player getPlayer() {
        return player;
    }

    public void move(Movable.Direction direction) {
        Pair<Integer, Integer> pos = this.player.getPosition();
        switch (direction) {
            case LEFT:
                pos.setFirst(Math.max(pos.getFirst() - 1, 1));
                break;
            case FORWARD:
                pos.setSecond(Math.max(pos.getSecond() - 1, 1));
                break;
            case BACKWARD:
                pos.setSecond(Math.min(pos.getSecond() + 1, HEIGHT - 2));
                break;
            case RIGHT:
                pos.setFirst(Math.min(pos.getFirst() + 1, WIDTH - 2));
                break;
        }

        Iterator<Entity> it = field.iterator();
        while (it.hasNext()) {
            Entity entity = it.next();
            if (entity.getPosition().equals(pos)) {
                if (entity instanceof Item) {
                    it.remove();
                    render.renderField();
                } else if (entity instanceof Trap) {
                    player.takeDamage(((Trap) entity).getDamage());
                    render.renderStatus();
                } else if (entity instanceof Door) {
//                    if (player.hasKey()) {
//                        it.remove();
//                    }
                }
            }
        }
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(value, max));
    }
}
