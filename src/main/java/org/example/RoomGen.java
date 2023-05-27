package org.example;

import java.util.*;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
import net.slashie.util.Pair;

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

    public List<Pair<Integer, Integer>> artifactSpots() {
        List<Pair<Integer, Integer>> artifacts = new LinkedList<>();
        for (int i = 0; i < artifactsCount; ++i) {
            int x1 = random.nextInt(width - 1) + xdelta + 1, y1 = random.nextInt(height - 1) + ydelta + 1;
            Pair<Integer, Integer> toAdd = new Pair<>(x1, y1);
            while (important.contains(toAdd)) {
                x1 = random.nextInt(width) + xdelta;
                y1 = random.nextInt(height) + ydelta;
                toAdd = new Pair<>(x1, y1);
            }
            artifacts.add(toAdd);
            important.add(toAdd);
        }
        return artifacts;
    }

    public List<Pair<Integer, Integer>> enemySpots() {
        List<Pair<Integer, Integer>> enemies = new LinkedList<>();
        for (int i = 0; i < enemiesCount; ++i) {
            int x1 = random.nextInt(width - 1) + xdelta + 1, y1 = random.nextInt(height - 1) + ydelta + 1;
            Pair<Integer, Integer> toAdd = new Pair<>(x1, y1);
            while (important.contains(toAdd)) {
                x1 = random.nextInt(width) + xdelta;
                y1 = random.nextInt(height) + ydelta;
                toAdd = new Pair<>(x1, y1);
            }
            enemies.add(toAdd);
            important.add(toAdd);
        }
        return enemies;
    }

    private List<Pair<Integer, Integer>> traps() {
        List<Pair<Integer, Integer>> traps = new LinkedList<>();
        for (int i = 0; i < enemiesCount; ++i) {
            int x1 = random.nextInt(width - 1) + xdelta + 1, y1 = random.nextInt(height - 1) + ydelta + 1;
            Pair<Integer, Integer> toAdd = new Pair<>(x1, y1);
            while (important.contains(toAdd)) {
                x1 = random.nextInt(width) + xdelta;
                y1 = random.nextInt(height) + ydelta;
                toAdd = new Pair<>(x1, y1);
            }
            traps.add(toAdd);
            important.add(toAdd);
        }
        return traps;
    }
}
