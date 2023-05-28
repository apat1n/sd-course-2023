package org.example.render;

import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
import org.example.Level;
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

    public void render(Level level) {
        renderField(level);
        renderPlayer(level);
        renderStatus(level);
    }

    private void renderEntity(Entity entity) {
        Pair<Integer, Integer> position = entity.getPosition();
        Utils.Palette palette = Utils.palette.get(entity.getClass().getName());
        csi.print(position.getFirst(), position.getSecond(), palette.fieldSymbol, palette.color);
    }

    public void renderField(Level level) {
        csi.cls();
        for (int x = 0; x < 80; ++x) {
            for (int y = 0; y < 19; ++y) {
                renderEntity(new Empty(new Pair<>(x, y)));
            }
        }
        for (Entity entity : level.getField()) {
            renderEntity(entity);
        }
        csi.saveBuffer();
    }

    public void renderDeath() {
        csi.cls();
        csi.print(0, 0, "You died!");
        csi.refresh();
    }

    public void renderStatus(Level level) {
        csi.restore();
        csi.print(0, level.getHeight(), new String(new char[level.getWidth()]).replace("\0", " "));
        csi.print(0, level.getHeight(), String.format("Health: %d", level.getPlayer().getHealth()));
        csi.saveBuffer();
    }

    public void renderPlayer(Level level) {
        csi.restore();
        renderEntity(level.getPlayer());
        csi.refresh();
    }

    public int getKey() {
        return csi.inkey().code;
    }
}
