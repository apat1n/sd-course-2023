package org.example;

import net.slashie.libjcsi.CharKey;
import org.example.entities.movable.Direction;
import org.example.render.Render;

public class Main {
    public static void main(String[] args) {
        Level level = new Level(0, 0, 2, 2);
//        Equipment equipment = new Equipment();
        Render render = new Render();
        render.renderField(level);
        render.renderStatus(level);
        while (true) {
            render.renderPlayer(level);

            Direction direction = Direction.NONE;
            switch (render.getKey()) {
                case CharKey.LARROW:
                    direction = Direction.LEFT;
                    break;
                case CharKey.UARROW:
                    direction = Direction.FORWARD;
                    break;
                case CharKey.DARROW:
                    direction = Direction.BACKWARD;
                    break;
                case CharKey.RARROW:
                    direction = Direction.RIGHT;
                    break;
                case CharKey.N1:
                    break;
                case CharKey.N2:
                    break;
                case CharKey.N3:
                    break;
                case CharKey.N4:
                    break;
                case CharKey.N5:
                    break;
                case CharKey.N6:
                    break;
                case CharKey.N7:
                    break;
                case CharKey.N8:
                    break;
                case CharKey.N9:
                    break;
                case CharKey.N0:
                    break;
                case CharKey.Q:
                case CharKey.q:
                    System.exit(0);
            }
            if (level.move(direction)) {
                System.out.println("[NEW LEVEL]");
                System.exit(0);
            }
            render.renderField(level);
            render.renderStatus(level);

            if (level.getPlayer().getHealth() <= 0) {
                render.renderDeath();
                loop:
                while (true) {
                    switch (render.getKey()) {
                        case CharKey.C:
                        case CharKey.c:
                            level = new Level(0, 0, 2, 2);
                            render.renderField(level);
                            render.renderStatus(level);
                            break loop;
                        case CharKey.Q:
                        case CharKey.q:
                            System.exit(0);
                    }
                }
            }
        }
    }
}
