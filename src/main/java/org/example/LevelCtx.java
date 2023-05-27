package org.example;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.ConsoleSystemInterface;

import org.example.entities.nonmovable.Trap;
import org.example.entities.nonmovable.ExitDoor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LevelCtx {
    public static final Integer WIDTH = 80;
    public static final Integer HEIGHT = 20;

    private final ExitDoor exitDoor;
    private final List<Trap> traps;
    private final List<Item> items;

    private final Map<Pair<Integer, Integer>, Pair<Character, CSIColor>> map;

    LevelCtx(ExitDoor exitDoor, List<Trap> traps, List<Item> items) {
        this.exitDoor = exitDoor;
        this.traps = traps;
        this.items = items;

        map = new HashMap<>();
        for (Trap trap : traps) {
            map.put(trap.getPosition(), new Pair<>('░', CSIColor.RED));
        }
        for (Item item : items) {
            map.put(item.getCoordinates(), new Pair<>('░', CSIColor.GREEN));
        }
    }

    private Pair<Character, CSIColor> getPixelAt(Integer x, Integer y) {
        if (y == 0 || y == HEIGHT - 1) {
            if (x == WIDTH / 2 || x == WIDTH / 2 - 1) {
                return new Pair<>('-', CSIColor.WHITE);
            } else {
                return new Pair<>('#', CSIColor.WHITE);
            }
        }
        if (x == 0 || x == WIDTH - 1) {
            if (y == HEIGHT / 2 || y == HEIGHT / 2 - 1) {
                return new Pair<>('|', CSIColor.WHITE);
            } else {
                return new Pair<>('#', CSIColor.WHITE);
            }
        }

        Pair<Character, CSIColor> pixel = this.map.get(new Pair<>(x, y));
        if (pixel != null) {
            return pixel;
        }
        return new Pair<>('.', CSIColor.WHITE);
    }

    public void render(ConsoleSystemInterface csi) {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Pair<Character, CSIColor> pixel = getPixelAt(x, y);
                csi.print(x, y, pixel.getFirst(), pixel.getSecond());
            }
        }
    }
}
