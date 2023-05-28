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
    private final int width;
    private final int height;
    private final int xOffset;
    private final int yOffset;

    Set<Pair<Integer, Integer>> important = new HashSet<>();

    public RoomGen(int xOffset, int yOffset, int width, int height) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.width = width;
        this.height = height;
    }

    private List<Pair<Integer, Integer>> generate(int number) {
        List<Pair<Integer, Integer>> result = new LinkedList<>();
        for (int i = 0; i < number; ++i) {
            int x1 = random.nextInt(width - 2) + xOffset + 1, y1 = random.nextInt(height - 2) + yOffset + 1;
            Pair<Integer, Integer> toAdd = new Pair<>(x1, y1);
            while (important.contains(toAdd)) {
                x1 = random.nextInt(width - 2) + xOffset + 1;
                y1 = random.nextInt(height - 2) + yOffset + 1;
                toAdd = new Pair<>(x1, y1);
            }
            result.add(toAdd);
            important.add(toAdd);
        }
        return result;
    }

    public List<Entity> getItems(int count) {
        List<Entity> result = new LinkedList<>();
        for (Pair<Integer, Integer> position : generate(count)) {
            result.add(new Item(position, "ABC", 1, 1));
        }
        return result;
    }


    public List<Entity> getTraps(int count) {
        List<Entity> result = new LinkedList<>();
        for (Pair<Integer, Integer> position : generate(count)) {
            result.add(new Trap(position, 10));
        }
        return result;
    }

    /*public List<Entity> getEnemies(int count) {
        List<Entity> result = new LinkedList<>();
        for (Pair<Integer, Integer> position : generate(count)) {
            result.add(new Enemy(position, "ABC", 1, 1));
        }
        return result;
    }*/
}
