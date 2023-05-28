package org.example;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.ConsoleSystemInterface;

import org.example.entities.nonmovable.Item;
import org.example.entities.nonmovable.Trap;
import org.example.entities.nonmovable.ExitDoor;

import java.util.ArrayList;
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

    LevelCtx(ExitDoor exitDoor) {
        this.exitDoor = exitDoor;
        RoomGen roomGen = new RoomGen(0, 0, HEIGHT, WIDTH, 2, 0, 2);
        this.traps = roomGen.traps();
        this.items = roomGen.artifactSpots();

        map = new HashMap<>();
        for (Trap trap : traps) {
            System.out.println(trap);
            map.put(trap.getPosition(), new Pair<>('T', CSIColor.RED));
        }

        List<Item> itemList = roomGen.artifactSpots();
        for (Item item : items) {
            map.put(item.getPosition(), new Pair<>('I', CSIColor.GREEN));
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
