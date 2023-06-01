package org.example;

import org.example.entities.Entity;
import org.example.entities.movable.Direction;
import org.example.entities.movable.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Game {
    private final List<Level> levels;
    private final Player player;

    private Pair<Integer, Integer> initializePosition() {
        return new Pair<>(0, levels.get(levels.size() - 1).getHeight() / 2);
    }

    public Game() {
        this.levels = new LinkedList<>(List.of(new Level(2, 2)));
        this.player = new Player(initializePosition());
    }

    public int getWidth() {
        return levels.get(player.getLevelNumber()).getWidth();
    }

    public int getHeight() {
        return levels.get(player.getLevelNumber()).getHeight();
    }

    public Map<Pair<Integer, Integer>, Entity> getField() {
        return levels.get(player.getLevelNumber()).getField();
    }

    public Player getPlayer() {
        return player;
    }

    public void move(Direction direction) {
        Level curLevel = levels.get(player.getLevelNumber());
        if (curLevel.move(player, direction)) {
            System.out.println("[NEW LEVEL]");
            levels.add(new Level(2, 2));
            player.setLevelNumber(player.getLevelNumber() + 1);
            player.setPosition(initializePosition());
        }
    }
}
