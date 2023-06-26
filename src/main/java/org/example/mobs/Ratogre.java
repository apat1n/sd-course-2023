package org.example.mobs;

import org.example.Level;
import org.example.Pair;
import org.example.entities.Entity;
import org.example.entities.nonmovable.Door;
import org.example.entities.nonmovable.Wall;

public class Ratogre extends Mob{
    Ratogre(Strategy strategy, Pair<Integer, Integer> location) {
        super(strategy);
        myLocation = location;
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return myLocation;
    }

    public Ratogre(Pair<Integer, Integer> myPos, Level level) {
        super(new BraveStrategy(), myPos, level);
        this.xp = 200;
        this.healthPoints = 50;
        this.attack = 10;
    }

    @Override
    public void move() {
        updatePlayerLocation(level.getPlayerPosition());
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
            if (!(tile instanceof Door) && !(tile instanceof Wall) && !(tile instanceof Mob)){
                checker += 1 << i;
            }
        }
        if (strategy.decide(playerLocation, this.myLocation, checker)){
            level.getPlayer().takeDamage(attack);
        }
    }

    @Override
    public void setPosition(Pair<Integer, Integer> position) {
        myLocation = position;
    }
}
