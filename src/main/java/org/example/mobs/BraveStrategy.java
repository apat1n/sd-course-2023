package org.example.mobs;

import org.example.Pair;
import org.example.entities.Entity;
import org.example.entities.nonmovable.Door;
import org.example.entities.nonmovable.Wall;

import static java.lang.Math.abs;

public class BraveStrategy implements Strategy{
    @Override
    public boolean decide(Pair<Integer, Integer> playerLocation, Pair<Integer, Integer> myLocation, int availableSpots) {
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
            int deltaNew = abs(newPos.getFirst() - playerLocation.getFirst()) + abs(newPos.getSecond() - playerLocation.getSecond()),
                deltaOld = abs(myLocation.getFirst() - playerLocation.getFirst()) + abs(myLocation.getSecond() - playerLocation.getSecond());
            if (deltaOld - deltaNew > 0 && (availableSpots & (1 << i)) > 0 && (!newPos.equals(playerLocation))){
                myLocation.setFirst(newPos.getFirst());
                myLocation.setSecond(newPos.getSecond());
                return false;
            } else if (newPos.equals(playerLocation)){
                return true;
            }
        }
        return false;
    }
}
