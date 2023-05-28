package org.example.render;

import net.slashie.libjcsi.CSIColor;

public class Palette<SymbolT> {
    public final SymbolT symbol;
    public final CSIColor color;

    public Palette(SymbolT symbol, CSIColor color) {
        this.symbol = symbol;
        this.color = color;
    }
}
