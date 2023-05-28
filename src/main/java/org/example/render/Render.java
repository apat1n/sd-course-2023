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

    public Render() {
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

    public void renderField(LevelCtx levelCtx) {
        csi.cls();
        for (int x = 0; x < LevelCtx.getWidth(); ++x) {
            for (int y = 0; y < LevelCtx.getHeight(); ++y) {
                renderEntity(new Empty(new Pair<>(x, y)));
            }
        }
        for (Entity entity : levelCtx.getField()) {
            renderEntity(entity);
        }
        csi.saveBuffer();
        renderStatus(levelCtx);
    }

    public void renderDeath() {
        csi.cls();
        csi.print(0, 0, "You died!");
        csi.refresh();
    }

    public void renderStatus(LevelCtx levelCtx) {
        csi.restore();
        csi.print(0, LevelCtx.getHeight(), new String(new char[LevelCtx.getWidth()]).replace("\0", " "));
        csi.print(0, LevelCtx.getHeight(), "Health: " + levelCtx.getPlayer().getHealth());
        csi.saveBuffer();
    }

    public void renderPlayer(LevelCtx levelCtx) {
        csi.restore();
        renderEntity(levelCtx.getPlayer());
        csi.refresh();
    }

    public int getKey() {
        return csi.inkey().code;
    }
}
