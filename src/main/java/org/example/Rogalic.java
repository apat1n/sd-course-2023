package org.example;

import net.slashie.libjcsi.CharKey;
import org.example.entities.movable.Movable;
import org.example.render.Render;

public class Rogalic {
    public void run() {
        LevelCtx levelCtx = new LevelCtx(null);
        Render render = new Render(levelCtx);
        render.renderField();

        while (true) {
            render.renderPlayer();

            Movable.Direction direction = Movable.Direction.NONE;
            switch (render.getKey()) {
                case CharKey.LARROW:
                    direction = Movable.Direction.LEFT;
                    break;
                case CharKey.UARROW:
                    direction = Movable.Direction.FORWARD;
                    break;
                case CharKey.DARROW:
                    direction = Movable.Direction.BACKWARD;
                    break;
                case CharKey.RARROW:
                    direction = Movable.Direction.RIGHT;
                    break;
                case CharKey.Q:
                case CharKey.q:
                    System.exit(0);
            }
            levelCtx.move(direction);
        }
    }
}
