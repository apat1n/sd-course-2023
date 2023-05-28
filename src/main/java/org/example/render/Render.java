package org.example.render;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
import org.example.Pair;
import org.example.entities.Entity;
import org.example.entities.movable.Player;
import org.example.entities.nonmovable.Empty;

import java.util.Properties;
import java.util.Set;

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

    public void renderField(int width, int height, Set<Entity> field) {
        csi.cls();
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                renderEntity(new Empty(new Pair<>(x, y)));
            }
        }
        for (Entity entity : field) {
            renderEntity(entity);
        }
        csi.saveBuffer();
    }

    public void renderPlayer(Player player) {
        csi.restore();
        renderEntity(player);
        csi.refresh();
    }

    public int getKey() {
        return csi.inkey().code;
    }
}
