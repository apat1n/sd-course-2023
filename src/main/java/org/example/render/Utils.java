package org.example.render;

import net.slashie.libjcsi.CSIColor;

import java.util.Map;

public class Utils {
    public static class Palette {
        public char fieldSymbol;
        public CSIColor color;

        public Palette(char fieldSymbol, CSIColor color) {
            this.fieldSymbol = fieldSymbol;
            this.color = color;
        }
    }

    public static final Map<String, Palette> palette = Map.of(
            "org.example.entities.movable.Player", new Palette('@', CSIColor.AQUA),
            "org.example.entities.nonmovable.Door", new Palette('/', CSIColor.WHITE),
            "org.example.entities.nonmovable.Empty", new Palette('.', CSIColor.WHITE),
            "org.example.entities.nonmovable.Item", new Palette('o', CSIColor.GREEN),
            "org.example.entities.nonmovable.Trap", new Palette('x', CSIColor.RED),
            "org.example.entities.nonmovable.Wall", new Palette('#', CSIColor.WHITE),
            "org.example.entities.nonmovable.Hatch", new Palette('O', CSIColor.AQUA)
    );
}
