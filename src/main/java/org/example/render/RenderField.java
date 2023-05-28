package org.example.render;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.ConsoleSystemInterface;
import org.example.Level;
import org.example.Pair;
import org.example.entities.Entity;
import org.example.entities.movable.Player;
import org.example.entities.nonmovable.Empty;

import java.util.Map;

public class RenderField {
    private static final int HEIGHT = 19;
    private final int xOffset;
    private final int yOffset;
    private final ConsoleSystemInterface csi;
    private static final Map<String, Palette<Character>> palette = Map.of(
            "org.example.entities.movable.Player", new Palette<>('@', CSIColor.AQUA),
            "org.example.entities.nonmovable.Door", new Palette<>('/', CSIColor.WHITE),
            "org.example.entities.nonmovable.Empty", new Palette<>('.', CSIColor.WHITE),
            "org.example.entities.nonmovable.Item", new Palette<>('o', CSIColor.GREEN),
            "org.example.entities.nonmovable.Trap", new Palette<>('x', CSIColor.RED),
            "org.example.entities.nonmovable.Wall", new Palette<>('#', CSIColor.WHITE),
            "org.example.entities.nonmovable.Hatch", new Palette<>('O', CSIColor.AQUA)
    );

    public RenderField(ConsoleSystemInterface csi, int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.csi = csi;
    }

    public int getWidth() {
        return 80;
    }
    public int getHeight() {
        return HEIGHT;
    }

    private void renderEntity(Entity entity) {
        Pair<Integer, Integer> position = entity.getPosition();
        Palette<Character> palette = RenderField.palette.get(entity.getClass().getName());
        csi.print(position.getFirst() + xOffset, position.getSecond() + yOffset, palette.symbol, palette.color);
    }

    public void render(Level level) {
        for (int x = 0; x < getWidth(); ++x) {
            for (int y = 0; y < level.getHeight(); ++y) {
                renderEntity(new Empty(new Pair<>(x + xOffset, y + yOffset)));
            }
        }
        for (Entity entity : level.getField()) {
            renderEntity(entity);
        }
    }

    public void render(Player player) {
        renderEntity(player);
    }
}
