package org.example.entities.movable;

import org.example.Equipment;
import org.example.Pair;
import org.example.entities.nonmovable.Item;
import org.example.entities.nonmovable.Trap;
import org.example.mobs.Strategy;

public class Player implements Movable {
    private static final int INITIAL_HEALTH = 10, INITIAL_ATTACK = 5;
    private Pair<Integer, Integer> position;
    private int health = INITIAL_HEALTH, attack = INITIAL_ATTACK, xp = 0;
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

    public int getAttack() {
        return attack;
    }

    public boolean apply(Item item) {
        return getEquipment().add(item);
    }

    public void apply(Trap trap) {
        takeDamage(trap.getDamage());
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void giveXP(int xp){
        this.xp += xp;
        if (this.xp > 100){
            this.xp = 0;
            attack += 2;
            health += 10;
        }
    }

    public void takeDamage(int damage){
        this.health -= damage;
    }
}
