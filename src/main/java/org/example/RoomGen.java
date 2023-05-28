package org.example;

import java.util.*;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
import org.example.entities.nonmovable.Item;
import org.example.entities.nonmovable.Trap;

public class RoomGen {
    private int height, width, artifactsCount, enemiesCount, trapCount;
    private int xdelta, ydelta;
    private static final Random random = new Random();

    Set<Pair<Integer, Integer>> important = new HashSet<>();

    public RoomGen(int xdelta, int ydelta, int height, int width, int artifactsCount, int enemiesCount, int trapCount) {
        this.xdelta = xdelta;
        this.ydelta = ydelta;
        this.height = height;
        this.width = width;
        this.artifactsCount = artifactsCount;
        this.enemiesCount = enemiesCount;
        this.trapCount = trapCount;
    }

    private List<Pair<Integer, Integer>> generateCoords(int number){
        List<Pair<Integer, Integer>> coords = new LinkedList<>();
        for (int i = 0; i<number; ++i){
            int x1 = random.nextInt(width - 2) + xdelta + 1, y1 = random.nextInt(height - 2) + ydelta + 1;
            Pair<Integer, Integer> toAdd = new Pair<>(x1, y1);
            while (important.contains(toAdd)) {
                x1 = random.nextInt(width - 2) + xdelta + 1;
                y1 = random.nextInt(height - 2) + ydelta + 1;
                toAdd = new Pair<>(x1, y1);
            }
            coords.add(toAdd);
            important.add(toAdd);
        }
        return coords;
    }

    public List<Item> artifactSpots() {
        List<Item> artifacts = new LinkedList<>();
        for (Pair <Integer, Integer> curCord : generateCoords(artifactsCount)) {
            artifacts.add(new Item(curCord, "ABC", 1, 1));
        }
        return artifacts;
    }

    public List<Pair<Integer, Integer>> enemySpots() {
        List<Pair<Integer, Integer>> enemies = new LinkedList<>();
        for (int i = 0; i < enemiesCount; ++i) {
            int x1 = random.nextInt(width - 2) + xdelta + 1, y1 = random.nextInt(height - 2) + ydelta + 1;
            Pair<Integer, Integer> toAdd = new Pair<>(x1, y1);
            while (important.contains(toAdd)) {
                x1 = random.nextInt(width - 2) + xdelta + 1;
                y1 = random.nextInt(height - 2) + ydelta + 1;
                toAdd = new Pair<>(x1, y1);
            }
            enemies.add(toAdd);
            important.add(toAdd);
        }
        return enemies;
    }

    public List<Trap> traps() {
        List<Trap> traps = new LinkedList<>();
        for (Pair <Integer, Integer> curCord : generateCoords(trapCount)) {
            traps.add(new Trap(curCord, 10));
        }
        return traps;
    }
}
