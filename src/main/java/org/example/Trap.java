package org.example;

public class Trap {
    private Integer attackPower;
    private Pair<Integer, Integer> coordinates;

    public Trap(Pair<Integer, Integer> coordinates) {
        this.coordinates = coordinates;
    }

    public Pair<Integer, Integer> getCoordinates() {
        return coordinates;
    }
}
