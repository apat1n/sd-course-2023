package org.example;

import net.slashie.libjcsi.CharKey;
import org.example.entities.movable.Direction;
import org.example.render.Render;

public class Main {
    public static void main(String[] args) {
        Level level = new Level(2, 2);
        Render render = new Render();
        render.renderField(level);
        render.renderEquipment(level.getPlayer().getEquipment());
        render.renderStatus(level.getPlayer());

        while (true) {
            render.renderPlayer(level.getPlayer());

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
                case CharKey.Q:
                case CharKey.q:
                    System.exit(0);
            }
            if (level.move(direction)) {
                System.out.println("[NEW LEVEL]");
                System.exit(0);
            }
            render.renderField(level);
            render.renderEquipment(level.getPlayer().getEquipment());
            render.renderStatus(level.getPlayer());

            if (level.getPlayer().getHealth() <= 0) {
                render.renderDeath();
                loop:
                while (true) {
                    switch (render.getKey()) {
                        case CharKey.C:
                        case CharKey.c:
                            level = new Level(2, 2);
                            render.renderField(level);
                            render.renderStatus(level.getPlayer());
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
