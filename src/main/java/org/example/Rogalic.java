package org.example;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

public class Rogalic {
    private ConsoleSystemInterface csi = new WSwingConsoleInterface("Simple Roguelike - libjcsi Testing Grounds", true);
    private int a, b;

    public static void main(String[] p) {
        new Rogalic().run();
    }

    public void run() {
        csi.cls();

        LevelCtx levelCtx = new LevelCtx();
        levelCtx.render(csi);
        csi.saveBuffer();
        csi.saveBuffer();

        a = 10;
        boolean exit = false;
        while (!exit) {
            csi.restore();
            csi.restore();
            csi.print(a, b, '@', CSIColor.ATOMIC_TANGERINE);
            csi.refresh();

            int key = csi.inkey().code;
            switch (key) {
                case CharKey.UARROW:
                    b--;
                    break;
                case CharKey.DARROW:
                    b++;
                    break;
                case CharKey.LARROW:
                    a--;
                    break;
                case CharKey.RARROW:
                    a++;
                    break;
                case CharKey.Q:
                case CharKey.q:
                    exit = true;
            }
        }
        csi.print(1, 20, "Press space to continue");
        csi.refresh();
        csi.waitKey(CharKey.SPACE);
        System.exit(0);
    }
}