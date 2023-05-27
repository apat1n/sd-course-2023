package org.example;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Rogalic {
    private final Properties configuration = new Properties();
    private ConsoleSystemInterface csi;

    Rogalic() {
        configuration.setProperty("fontSize", "20");
        configuration.setProperty("font", "SF Mono Regular");
        csi = new WSwingConsoleInterface("Heresy Rising", configuration);
    }

    public static void main(String[] p) {
        new Rogalic().run();
    }

    public void run() {
        csi.cls();

        List<Trap> traps = Arrays.asList(
                new Trap(new Pair(40, 15)),
                new Trap(new Pair(50, 7))
        );
        List<Item> items = Arrays.asList(
                new Item(new Pair(10, 10)),
                new Item(new Pair(20, 10))
        );
        LevelCtx levelCtx = new LevelCtx(null, traps, items);
        levelCtx.render(csi);
        csi.saveBuffer();
        csi.saveBuffer();

        int x = 10;
        int y = 10;
        boolean exit = false;
        while (!exit) {
            csi.restore();
            csi.restore();
            csi.print(x, y, '@', CSIColor.AQUA);
            csi.refresh();

            int key = csi.inkey().code;
            switch (key) {
                case CharKey.UARROW:
                    y = Math.max(1, y - 1);
                    break;
                case CharKey.DARROW:
                    y = Math.min(LevelCtx.HEIGHT - 2, y + 1);
                    break;
                case CharKey.LARROW:
                    x = Math.max(1, x - 1);
                    break;
                case CharKey.RARROW:
                    x = Math.min(LevelCtx.WIDTH - 2, x + 1);
                    break;
                case CharKey.Q:
                case CharKey.q:
                    exit = true;
            }
        }
        System.exit(0);
    }
}