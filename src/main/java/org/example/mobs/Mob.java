package org.example.mobs;

import org.example.Level;
import org.example.Pair;
import org.example.entities.movable.Movable;

import java.util.Random;

public abstract class Mob implements Movable {
    int healthPoints, attack, xp;
    Level level;
    Strategy strategy;
    Pair<Integer, Integer> playerLocation, myLocation;

    Mob(Strategy strategy){
        this.strategy = strategy;
    }

    Mob(Strategy strategy, Pair<Integer, Integer> myLocation, Level level){
        this.strategy = strategy;
        this.myLocation = myLocation;
        this.playerLocation = level.getPlayer().getPosition();
        this.level = level;
    }

    public void move(){
    }

    public boolean decresaseHealth(int attacked){
        Random random = new Random();
        this.healthPoints -= attacked;
        return healthPoints <= 0;
    }

    public void updatePlayerLocation(Pair<Integer, Integer> playerLocation){
        this.playerLocation = playerLocation;
    }

    public int giveXP(){
        return xp;
    }
}
