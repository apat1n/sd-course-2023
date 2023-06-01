package org.example;


import org.example.entities.Entity;
import org.example.entities.nonmovable.Boost;
import org.example.entities.nonmovable.Loot;
import org.example.entities.nonmovable.Trap;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class RoomGen {
    private static final Random random = new Random();
    private final int xOffset;
    private final int yOffset;
    private final int width;
    private final int height;
    private final Set<Pair<Integer, Integer>> used = new HashSet<>();

    public RoomGen(int xOffset, int yOffset, int width, int height) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.width = width;
        this.height = height;
    }

    private List<Pair<Integer, Integer>> generate(int number) {
        List<Pair<Integer, Integer>> result = new LinkedList<>();
        for (int i = 0; i < number; ++i) {
            Pair<Integer, Integer> toAdd = new Pair<>(
                    random.nextInt(width) + xOffset,
                    random.nextInt(height) + yOffset
            );
            while (used.contains(toAdd)) {
                toAdd = new Pair<>(
                        random.nextInt(width) + xOffset,
                        random.nextInt(height) + yOffset
                );
            }
            result.add(toAdd);
            used.add(toAdd);
        }
        return result;
    }

    public List<Entity> getItems(int count) {
        List<Entity> result = new LinkedList<>();
        for (Pair<Integer, Integer> position : generate(count)) {
            String name = "ABC";
            int boostAttack = 10;
            int boostHealth = 10;
            switch (random.nextInt(2)) {
                case 0:
                    result.add(new Boost(position, name, boostAttack, boostHealth));
                    break;
                case 1:
                    result.add(new Loot(position, name, boostAttack, boostHealth));
            }
        }
        return result;
    }


    public List<Entity> getTraps(int count) {
        List<Entity> result = new LinkedList<>();
        for (Pair<Integer, Integer> position : generate(count)) {
            int damage = 10;
            result.add(new Trap(position, damage));
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
