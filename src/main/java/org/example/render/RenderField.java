package org.example.render;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.ConsoleSystemInterface;
import org.example.Game;
import org.example.Level;
import org.example.Pair;
import org.example.entities.Entity;
import org.example.entities.movable.Player;
import org.example.entities.nonmovable.Empty;

import java.util.HashMap;
import java.util.Map;

public class RenderField {
    private static final int HEIGHT = 19;
    private final int xOffset;
    private final int yOffset;
    private final ConsoleSystemInterface csi;
    private static Map<String, Palette<Character>> palette = new HashMap<String, Palette<Character>>();

    public RenderField(ConsoleSystemInterface csi, int xOffset, int yOffset) {
        palette.putAll(Map.of(
                "org.example.entities.movable.Player", new Palette<>('@', CSIColor.AQUA),
                "org.example.entities.nonmovable.Boost", new Palette<>('o', CSIColor.GREEN),
                "org.example.entities.nonmovable.Door", new Palette<>('/', CSIColor.WHITE),
                "org.example.entities.nonmovable.Empty", new Palette<>('.', CSIColor.WHITE),
                "org.example.entities.nonmovable.Loot", new Palette<>('o', CSIColor.GREEN),
                "org.example.entities.nonmovable.Trap", new Palette<>('x', CSIColor.RED),
                "org.example.entities.nonmovable.Wall", new Palette<>('#', CSIColor.WHITE),
                "org.example.entities.nonmovable.Hatch", new Palette<>('O', CSIColor.AQUA),
                "org.example.mobs.Skaven", new Palette<>('s', CSIColor.AMBER),
                "org.example.mobs.Rat", new Palette<>('r', CSIColor.AMBER)
        ));
        palette.put("org.example.mobs.Cultist", new Palette<>('c', CSIColor.AMBER));
        palette.put("org.example.mobs.ChaosWarrior", new Palette<>('C', CSIColor.AMETHYST));
        palette.put("org.example.mobs.Ratogre", new Palette<>('R', CSIColor.AMETHYST));
        System.out.println(palette.size());
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

    public void render(Game game) {
        for (int x = 0; x < getWidth(); ++x) {
            for (int y = 0; y < game.getHeight(); ++y) {
                renderEntity(new Empty(new Pair<>(x + xOffset, y + yOffset)));
            }
        }
        for (Entity entity : game.getField().values()) {
            renderEntity(entity);
        }
    }

    public void render(Player player) {
        renderEntity(player);
    }
}
