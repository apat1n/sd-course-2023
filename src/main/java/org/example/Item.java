package org.example;

public class Item {
    private String name;
    private Integer boostHealth;
    private Integer boostAttack;
    private Pair<Integer, Integer> coordinates;

    public Item(Pair<Integer, Integer> coordinates) {
        this.coordinates = coordinates;
    }

    public Pair<Integer, Integer> getCoordinates() {
        return coordinates;
    }
}
