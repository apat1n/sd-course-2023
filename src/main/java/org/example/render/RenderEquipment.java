package org.example.render;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.ConsoleSystemInterface;
import org.example.Equipment;
import org.example.entities.nonmovable.Loot;

import java.util.Map;

public class RenderEquipment {
    private static final int WIDTH = 3;
    private static final int HEIGHT = 3;
    private final int xOffset;
    private final int yOffset;
    private final ConsoleSystemInterface csi;
    private static final String EMPTY_KEY = "EMPTY";
    private static final Map<String, Palette<Character[][]>> palette = Map.of(
            EMPTY_KEY, new Palette<>(new Character[][]{{'.', '.', '.'}, {'.', '.', '.'}, {'.', '.', '.'}}, CSIColor.WHITE),
            "Sword", new Palette<>(new Character[][]{{'.', '*', '.'}, {'*', '*', '*'}, {'.', '*', '.'}}, CSIColor.WHITE),
            "Shield", new Palette<>(new Character[][]{{'.', '*', '.'}, {'*', '*', '*'}, {'.', '*', '.'}}, CSIColor.WHITE)
    );

    public RenderEquipment(ConsoleSystemInterface csi, int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.csi = csi;
    }

    public int getWidth() {
        return 80;
    }
    public int getHeight() {
        return HEIGHT + 2;
    }

    private void renderIcon(String name, int xOffset, int yOffset) {
        Palette<Character[][]> palette = RenderEquipment.palette.get(name);
        for (int x = 0; x < WIDTH; ++x) {
            for (int y = 0; y < HEIGHT; ++y) {
                csi.print(xOffset + x, yOffset + y, palette.symbol[x][y], palette.color);
            }
        }
    }

    private void renderItem(Loot loot, int xOffset, int yOffset) {
        for (int i = 0; i < WIDTH + 2; ++i) {
            csi.print(xOffset + i, yOffset, '#', CSIColor.WHITE);
            csi.print(xOffset + i, yOffset + HEIGHT + 1, '#', CSIColor.WHITE);
        }
        for (int i = 0; i < HEIGHT + 2; ++i) {
            csi.print(xOffset, yOffset + i, '#', CSIColor.WHITE);
            csi.print(xOffset + WIDTH + 1, yOffset + i, '#', CSIColor.WHITE);
            csi.print(xOffset + WIDTH + 2, yOffset + i, ' ', CSIColor.WHITE);
        }
        String name = loot != null ? loot.getName() : EMPTY_KEY;
        renderIcon(name, xOffset + 1, yOffset + 1);
    }

    public void render(Equipment equipment) {
        csi.print(xOffset, yOffset, "".repeat(getWidth()));
        for (int i = 0; i < equipment.size(); ++i) {
            renderItem(equipment.get(i), xOffset + i * (WIDTH + 3), yOffset);
        }
    }
}
