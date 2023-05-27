package org.example;

import net.slashie.libjcsi.ConsoleSystemInterface;

import java.util.List;

public class LevelCtx {
    private ExitDoor exitDoor;
    private List<Trap> traps;
    private List<Item> items;

    private static final Integer WIDTH = 80;
    private static final Integer HEIGHT = 20;

    private char getCharAt(Integer x, Integer y) {
        if (y == 0 || y == HEIGHT - 1) {
            if (x == WIDTH / 2 || x == WIDTH / 2 - 1) {
                return '-';
            } else {
                return '#';
            }
        }
        if (x == 0 || x == WIDTH - 1) {
            if (y == HEIGHT / 2 || y == HEIGHT / 2 - 1) {
                return '|';
            } else {
                return '#';
            }
        }
        return '.';
//        if (exitDoor.getCoordinates().getLeft().equals(x) && exitDoor.getCoordinates().getRight().equals(y)) {
//            return 'E';
//        }
//        for (Trap trap : traps) {
//            if (trap.getCoordinates().getLeft().equals(x) && trap.getCoordinates().getRight().equals(y)) {
//                return 'T';
//            }
//        }
//        for (Item item : items) {
//            if (item.getCoordinates().getLeft().equals(x) && item.getCoordinates().getRight().equals(y)) {
//                return 'I';
//            }
//        }
//        return '.';
    }

    public void render(ConsoleSystemInterface csi) {
        for (int y = 0; y < HEIGHT; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < WIDTH; x++) {
                sb.append(getCharAt(x, y));
            }
            csi.print(0, y, sb.toString());
        }
    }
}
