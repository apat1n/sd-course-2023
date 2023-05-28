package org.example.render;

import net.slashie.libjcsi.ConsoleSystemInterface;
import org.example.entities.movable.Player;

public class RenderStatus {
    private final int xOffset;
    private final int yOffset;
    private final ConsoleSystemInterface csi;
    private static final String STATUS_FORMAT = "Health: %-10d";

    public RenderStatus(ConsoleSystemInterface csi, int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.csi = csi;
    }

    public int getWidth() {
        return 80;
    }

    public int getHeight() {
        return 1;
    }

    public void render(Player player) {
        csi.print(xOffset, yOffset, "".repeat(getWidth()));
        csi.print(xOffset, yOffset, String.format(STATUS_FORMAT, player.getHealth()));
    }
}
