package org.example.entities.movable;

import org.example.Equipment;
import org.example.Pair;
import org.example.entities.nonmovable.Item;
import org.example.entities.nonmovable.Trap;

public class Player implements Movable {
    private static final int INITIAL_HEALTH = 10;
    private Pair<Integer, Integer> position;
    private int health = INITIAL_HEALTH;
    private final Equipment equipment;

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

    public int getHealth() {
        return health;
    }

    public boolean apply(Item item) {
        return getEquipment().add(item);
    }

    public void apply(Trap trap) {
        this.health -= trap.getDamage();
    }

    public Equipment getEquipment() {
        return equipment;
    }
}
