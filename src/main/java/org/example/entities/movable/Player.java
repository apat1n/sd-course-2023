package org.example.entities.movable;

import org.example.Equipment;
import org.example.Pair;
import org.example.entities.nonmovable.Boost;
import org.example.entities.nonmovable.Item;
import org.example.entities.nonmovable.Loot;
import org.example.entities.nonmovable.Trap;

public class Player implements Movable {
    private static final int INITIAL_ATTACK = 10;
    private static final int INITIAL_HEALTH = 10;
    private Pair<Integer, Integer> position;
    private int attack = INITIAL_ATTACK;
    private int health = INITIAL_HEALTH;
    private final Equipment equipment;
    private int levelNumber = 0;

    public Player(Pair<Integer, Integer> position) {
        this.position = position;
        this.equipment = new Equipment();
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    @Override
    public void setPosition(Pair<Integer, Integer> position) {
        this.position = position;
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }

    public boolean apply(Item item) {
        if (item instanceof Boost) {
            attack += item.getBoostAttack();
            health += item.getBoostHealth();
            return true;
        } else if (item instanceof Loot) {
            if (getEquipment().add((Loot) item)) {
                attack += item.getBoostAttack();
                health += item.getBoostHealth();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void apply(Trap trap) {
        this.health -= trap.getDamage();
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }
}
