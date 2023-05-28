package org.example.render;

import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
import org.example.LevelCtx;
import org.example.Pair;
import org.example.entities.Entity;
import org.example.entities.nonmovable.Empty;

import java.util.Properties;

public class Render {
    private final ConsoleSystemInterface csi;
    private final LevelCtx levelCtx;

    public Render(LevelCtx levelCtx) {
        this.levelCtx = levelCtx;

        Properties configuration = new Properties();
        configuration.setProperty("fontSize", "20");
        configuration.setProperty("font", "SF Mono Regular");
        csi = new WSwingConsoleInterface("Heresy Rising", configuration);
    }

    private void renderEntity(Entity entity) {
        Pair<Integer, Integer> position = entity.getPosition();
        Utils.Palette palette = Utils.palette.get(entity.getClass().getName());
        csi.print(position.getFirst(), position.getSecond(), palette.fieldSymbol, palette.color);
    }

    public void renderField() {
        csi.cls();
        for (int x = 0; x < levelCtx.WIDTH; ++x) {
            for (int y = 0; y < levelCtx.HEIGHT; ++y) {
                renderEntity(new Empty(new Pair<>(x, y)));
            }
        }
        for (Entity entity : levelCtx.getField()) {
            renderEntity(entity);
        }
        csi.saveBuffer();
    }

    public void renderPlayer() {
        csi.restore();
        renderEntity(levelCtx.getPlayer());
        csi.refresh();
    }

    public int getKey() {
        return csi.inkey().code;
    }
}
