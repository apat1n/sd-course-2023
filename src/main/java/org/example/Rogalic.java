package org.example;

import net.slashie.libjcsi.CharKey;
import org.example.entities.movable.Movable;
import org.example.render.Render;

public class Rogalic {
    public void run() {
        LevelCtx levelCtx = new LevelCtx(null);
        Render render = new Render(levelCtx);
        levelCtx.setRender(render);

        render.renderField();
        render.renderStatus();

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
            System.out.println(levelCtx.getPlayer().getHealth());

            if (levelCtx.getPlayer().getHealth() <= 0) {
                render.renderDeath();
                loop: while (true) {
                    switch (render.getKey()) {
                        case CharKey.C:
                        case CharKey.c:
                            levelCtx = new LevelCtx(null);
                            levelCtx.setRender(render);
                            render.setLevelCtx(levelCtx);

                            render.renderField();
                            render.renderStatus();

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
