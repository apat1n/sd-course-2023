package org.example.mobs;

import org.example.Pair;

public interface Strategy {
    public boolean decide(Pair<Integer, Integer> playerLocation, Pair<Integer, Integer> myLocation, int availableSpots);
}
