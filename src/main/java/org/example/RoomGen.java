package org.example;


import org.example.entities.Entity;
import org.example.entities.movable.Player;
import org.example.entities.nonmovable.Boost;
import org.example.entities.nonmovable.Loot;
import org.example.entities.nonmovable.Trap;
import org.example.mobs.Cultist;
import org.example.mobs.Mob;
import org.example.mobs.Rat;
import org.example.mobs.Skaven;

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

    /**
     *
     * @param count
     * @param levelNumber
     * @return
     */
    public List<Entity> getItems(int count, int levelNumber) {
        List<Entity> result = new LinkedList<>();
        for (Pair<Integer, Integer> position : generate(count)) {
            String name = "ABC";
            int boostAttack = 10;
            int boostHealth = 10;
            switch (random.nextInt(2)) {
                case 0:
                    result.add(new Boost(position, name, 0, boostHealth));
                    break;
                case 1:
                    result.add(generateItem(position, levelNumber));
            }
        }
        return result;
    }

    /**
     * Генерация предмета в зависимости от levelNumber. Больше - сильнее предмет.
     * @param position
     * @param levelNumber
     * @return
     */
    private Loot generateItem(Pair<Integer, Integer> position, int levelNumber){
        int boostAttack, boostHealth;
        switch (random.nextInt(2)) {
            case 0:                 //Меч
                boostAttack = 2 * levelNumber + random.nextInt(10);
                boostHealth = levelNumber;
                return (new Loot(position, "Sword", boostAttack, boostHealth));
            case 1:                 //Щит
                boostAttack = levelNumber;
                boostHealth = 2 * levelNumber + random.nextInt(10);
                return (new Loot(position, "Shield", boostAttack, boostHealth));
        }
        return null;
    }

    /**
     *
     * @param count
     * @param levelNumber
     * @return
     */
    public List<Entity> getTraps(int count, int levelNumber) {
        List<Entity> result = new LinkedList<>();
        for (Pair<Integer, Integer> position : generate(count)) {
            int damage = 10 * levelNumber;
            result.add(new Trap(position, damage));
        }
        return result;
    }

    public List<Mob> getEnemies(int levelNumber, Level level) {
        int skavenNumber = (levelNumber - 1) * 2, ratNumber = levelNumber, cultistNumber = 2 * levelNumber;
        List<Mob> result = new LinkedList<>();
        for (Pair<Integer, Integer> position : generate(skavenNumber)) {
            result.add(new Skaven(position, level));
        }
        for (Pair<Integer, Integer> position : generate(ratNumber)) {
            result.add(new Rat(position, level));
        }
        for (Pair<Integer, Integer> position : generate(cultistNumber)) {
            result.add(new Cultist(position, level));
        }
        return result;
    }
}
