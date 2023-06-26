package org.example;

import net.slashie.libjcsi.CharKey;
import org.example.entities.movable.Direction;
import org.example.render.Render;

public class Main {
    public static void main(String[] args) {
        while (true) {
            Game game = new Game();
            Render render = new Render();
            render.renderField(game);
            render.renderEquipment(game.getPlayer().getEquipment());
            render.renderStatus(game.getPlayer());
            do {
                render.renderPlayer(game.getPlayer());

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
                game.move(direction);
                game.moveEnemies();
                render.renderField(game);
                render.renderEquipment(game.getPlayer().getEquipment());
                render.renderStatus(game.getPlayer());
            } while (!game.isGameFinished() && game.getPlayer().getHealth() > 0);
            if (game.isGameFinished()){
                render.renderWin();
            } else {
                render.renderDeath();
            }
            death_loop:
            while (true) {
                switch (render.getKey()) {
                    case CharKey.C:
                    case CharKey.c:
                        break death_loop;
                    case CharKey.Q:
                    case CharKey.q:
                        System.exit(0);
                }
            }
        }
    }
}
