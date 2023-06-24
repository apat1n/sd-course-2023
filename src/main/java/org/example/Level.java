package org.example;


import org.example.entities.Entity;
import org.example.entities.movable.Direction;
import org.example.entities.movable.Player;
import org.example.entities.nonmovable.Door;
import org.example.entities.nonmovable.Hatch;
import org.example.entities.nonmovable.Item;
import org.example.entities.nonmovable.Trap;
import org.example.mobs.Mob;
import org.example.mobs.Skaven;

import java.util.*;

public class Level {
    private final int itemsCount;
    private final int trapsCount;
    private final Map<Pair<Integer, Integer>, Entity> field;
    private final List<Mob> enemies;
    private final List<Room> rooms;
    private Pair<Integer, Integer> playerPosition;
    private Player player;
    private int levelNumber = 0;

    public Level(int itemsCount, int trapsCount, int levelNumber) {
        this.levelNumber = levelNumber;
        this.itemsCount = itemsCount;
        this.trapsCount = trapsCount;
        this.enemies = new ArrayList<>();
        field = new HashMap<>();
        rooms = new LinkedList<>(List.of(new Room(field, enemies, 0, 0, itemsCount, trapsCount, false, levelNumber, this)));
    }

    public Level(int itemsCount, int trapsCount, int levelNumber, Player player) {
        this.levelNumber = levelNumber;
        this.player = player;
        this.itemsCount = itemsCount;
        this.trapsCount = trapsCount;
        this.enemies = new ArrayList<>();
        field = new HashMap<>();
        rooms = new LinkedList<>(List.of(new Room(field, enemies, 0, 0, itemsCount, trapsCount, false, levelNumber, this)));
    }

    public int getWidth() {
        return rooms.stream().map(Room::getWidth).reduce(0, Integer::sum);
    }

    public int getHeight() {
        return rooms.stream().map(Room::getHeight).max(Integer::compareTo).orElse(0);
    }

    public Map<Pair<Integer, Integer>, Entity> getField() {
        return field;
    }

    public boolean move(Player player, Direction direction) {
        int dx = 0, dy = 0;
        switch (direction) {
            case LEFT:
                dx = -1;
                break;
            case FORWARD:
                dy = -1;
                break;
            case BACKWARD:
                dy = 1;
                break;
            case RIGHT:
                dx = 1;
                break;
        }

        Pair<Integer, Integer> newPos = new Pair<>(
                player.getPosition().getFirst() + dx,
                player.getPosition().getSecond() + dy
        );
        Entity entity = field.get(newPos);
        if (entity == null) {
            if (newPos.getFirst() >= 0) {
                player.setPosition(newPos);
            }
        } else if (entity instanceof Item) {
            if (player.apply((Item) entity)) {
                field.remove(newPos);
            }
            player.setPosition(newPos);
        } else if (entity instanceof Trap) {
            player.apply((Trap) entity);
            player.setPosition(newPos);
        } else if (entity instanceof Door) {
            Door door = (Door) entity;
            player.setPosition(newPos);
            if (!door.getVisited()) {
                door.setVisited(true);
                if (rooms.size() == 3) {
                    rooms.add(new Room(field, enemies, getWidth(), 0, 0, 0, true, levelNumber, this));
                } else {
                    rooms.add(new Room(field, enemies, getWidth(), 0, itemsCount, trapsCount, false, levelNumber, this));
                }
            }
        } else if (entity instanceof Hatch) {
            ((Hatch) entity).isAvailable(true);
            return ((Hatch) entity).isAvailable();
        } else if (entity instanceof Mob){
            if (((Mob) entity).decresaseHealth(player.getAttack())){
                player.giveXP(((Mob) entity).giveXP());
                field.remove(newPos);
                player.setPosition(newPos);
                enemies.remove(entity);
            }
        }
        return false;
    }

    public void moveEnemies(Player player){
        this.player = player;
        playerPosition = player.getPosition();
        for (Mob enemy : enemies){
            Pair<Integer,Integer> oldCords = enemy.getPosition();
            field.remove(oldCords);
            enemy.move();
            field.put(enemy.getPosition(), enemy);
        }
    }

    public Player getPlayer(){
        return player;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public Pair<Integer, Integer> getPlayerPosition(){
        return playerPosition;
    }

    public void hurtPlayer(int damage){
        player.takeDamage(damage);
    }

    public Entity getTile(Pair<Integer, Integer> coords){
        return field.get(coords);
    }
}
