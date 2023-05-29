package org.example.mobs;

import org.example.Level;
import org.example.Pair;
import org.example.entities.Entity;
import org.example.entities.nonmovable.Door;
import org.example.entities.nonmovable.Wall;

public class Skaven extends Mob{
    Skaven(Strategy strategy, Pair<Integer, Integer> location) {
        super(strategy);
        myLocation = location;
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return myLocation;
    }

    public Skaven(Pair<Integer, Integer> myPos, Level level) {
        super(new BraveStrategy(), myPos, level);
        this.xp = 30;
        this.healthPoints = 10;
        this.attack = 3;
    }

    @Override
    public void move() {
        updatePlayerLocation();
        int checker = 0;
        for (int i = 0; i<4; ++i) {
            int dx = 0, dy = 0;
            switch (i) {
                case 0:
                    dx = -1;
                    break;
                case 1:
                    dy = -1;
                    break;
                case 2:
                    dy = 1;
                    break;
                case 3:
                    dx = 1;
                    break;
            }
            Pair<Integer, Integer> newPos = new Pair<>(myLocation.getFirst() + dx, myLocation.getSecond() + dy);
            Entity tile = level.getTile(newPos);
            if (!(tile instanceof Door) && !(tile instanceof Wall)){
                checker += 1 << i;
            }
        }
        if (strategy.decide(playerLocation, this.myLocation, checker)){
            level.getPlayer().takeDamage(attack);
        }
    }

    private void updatePlayerLocation(){
        this.playerLocation = level.getPlayer().getPosition();
    }

    @Override
    public void setPosition(Pair<Integer, Integer> position) {
        myLocation = position;
    }

}
