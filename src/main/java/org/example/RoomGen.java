package org.example;


import org.example.entities.Entity;
import org.example.entities.nonmovable.Item;
import org.example.entities.nonmovable.Trap;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class RoomGen {
    private static final Random random = new Random();
    private int width, height;
    private int xdelta, ydelta;

    Set<Pair<Integer, Integer>> important = new HashSet<>();

    public RoomGen(int xdelta, int ydelta, int width, int height) {
        this.xdelta = xdelta;
        this.ydelta = ydelta;
        this.width = width;
        this.height = height;
    }

    private List<Pair<Integer, Integer>> generateCoords(int number) {
        List<Pair<Integer, Integer>> coords = new LinkedList<>();
        for (int i = 0; i < number; ++i) {
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

    public List<Entity> artifactSpots(int count) {
        List<Entity> artifacts = new LinkedList<>();
        for (Pair<Integer, Integer> curCord : generateCoords(count)) {
            artifacts.add(new Item(curCord, "ABC", 1, 1));
        }
        return artifacts;
    }

    public List<Pair<Integer, Integer>> enemySpots(int count) {
        List<Pair<Integer, Integer>> enemies = new LinkedList<>();
        for (int i = 0; i < count; ++i) {
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


    public List<Entity> traps(int count) {
        List<Entity> traps = new LinkedList<>();
        for (Pair<Integer, Integer> curCord : generateCoords(count)) {
            traps.add(new Trap(curCord, 10));
        }
        return traps;
    }
}
