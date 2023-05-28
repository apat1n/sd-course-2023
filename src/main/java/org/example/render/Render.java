package org.example.render;

import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
import org.example.Equipment;
import org.example.Level;
import org.example.entities.movable.Player;

import java.util.Properties;

public class Render {
    private final ConsoleSystemInterface csi;
    private final RenderField renderField;
    private final RenderEquipment renderEquipment;
    private final RenderStatus renderStatus;

    public Render() {
        Properties configuration = new Properties();
        configuration.setProperty("fontSize", "20");
        configuration.setProperty("font", "SF Mono Regular");
        csi = new WSwingConsoleInterface("Heresy Rising", configuration);
        renderField = new RenderField(csi, 0, 0);
        renderEquipment = new RenderEquipment(csi, 0, renderField.getHeight());
        renderStatus = new RenderStatus(csi, 0, renderField.getHeight() + renderEquipment.getHeight());
    }

    public void renderField(Level level) {
        csi.cls();
        this.renderField.render(level);
        csi.saveBuffer();
    }

    public void renderEquipment(Equipment equipment) {
        csi.restore();
        this.renderEquipment.render(equipment);
        csi.saveBuffer();
    }

    public void renderStatus(Player player) {
        csi.restore();
        this.renderStatus.render(player);
        csi.saveBuffer();
    }

    public void renderPlayer(Player player) {
        csi.restore();
        this.renderField.render(player);
        csi.refresh();
    }

    public void renderDeath() {
        csi.cls();
        csi.print(0, 0, "You died!");
        csi.refresh();
    }

    public int getKey() {
        return csi.inkey().code;
    }
}
