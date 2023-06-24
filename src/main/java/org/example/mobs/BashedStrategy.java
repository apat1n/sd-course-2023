package org.example.mobs;

import org.example.Pair;

import java.util.Random;

import static java.lang.Math.abs;

public class BashedStrategy implements Strategy {
    @Override
    public boolean decide(Pair<Integer, Integer> playerLocation, Pair<Integer, Integer> myLocation, int availableSpots) {
        Random random = new Random();
        int dir = random.nextInt(5) % 4;
        int checker = 0;
        int dx = 0, dy = 0;
        switch (dir) {
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
        if ((availableSpots & (1 << dir)) > 0 && (!newPos.equals(playerLocation))) {
            myLocation.setFirst(newPos.getFirst());
            myLocation.setSecond(newPos.getSecond());
            return false;
        } else return newPos.equals(playerLocation);
    }
}
