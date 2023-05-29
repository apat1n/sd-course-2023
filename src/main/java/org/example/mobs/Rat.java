package org.example.mobs;

import org.example.Level;
import org.example.Pair;
import org.example.entities.Entity;
import org.example.entities.nonmovable.Door;
import org.example.entities.nonmovable.Wall;

public class Rat extends Mob{

    Rat(Strategy strategy) {
        super(strategy);
    }

    Rat(Pair<Integer, Integer> myLocation, Level level){
        super(new CowardStrategy(), myLocation, level);
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return myLocation;
    }

    @Override
    public void move() {
        int dx = 0, dy = 0, checker = 0;
        for (int i = 0; i<4; ++i) {
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
            strategy.decide(myLocation, playerLocation, checker);
        }
    }

    @Override
    public void setPosition(Pair<Integer, Integer> position) {
        myLocation = position;
    }
}
