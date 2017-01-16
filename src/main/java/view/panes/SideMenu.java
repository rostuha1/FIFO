package view.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.components.ComponentBuilder;

public class SideMenu extends VBox {

    private static final double width = 200;

    public static final SideMenu instance = new SideMenu();

    private SideMenu() {
        setAlignment(Pos.CENTER);

        Region generateBtn = ComponentBuilder.getButton("Generate", 20, width, 40, 0.5, Color.web("0x70FF00"), Color.web("0x1A4600"));
        Region addRowBtn = ComponentBuilder.getButton("Add row", 20, width, 40, 0.5, Color.web("0x70FF00"), Color.web("0x1A4600"));
        Region removeRowBtn = ComponentBuilder.getButton("Remove row", 20, width, 40, 0.5, Color.web("0x70FF00"), Color.web("0x1A4600"));
        Region addColumnBtn = ComponentBuilder.getButton("Add column", 20, width, 40, 0.5, Color.web("0x70FF00"), Color.web("0x1A4600"));
        Region removeColumnBtn = ComponentBuilder.getButton("Remove column", 20, width, 40, 0.5, Color.web("0x70FF00"), Color.web("0x1A4600"));
        Region emulateBtn = ComponentBuilder.getButton("Emulate", 20, width, 40, 0.5, Color.web("0x70FF00"), Color.web("0x1A4600"));

        generateBtn.setOnMouseClicked(event -> generateClick());
        addRowBtn.setOnMouseClicked(event -> addRowClick());
        addColumnBtn.setOnMouseClicked(event -> addColumnClick());
        removeRowBtn.setOnMouseClicked(event -> removeRowClick());
        removeColumnBtn.setOnMouseClicked(event -> removeColumnClick());
        emulateBtn.setOnMouseClicked(event -> emulate());

        Insets top = new Insets(10, 0, 0, 0);

        VBox.setMargin(generateBtn, Settings.verticalInsets);
        VBox.setMargin(addRowBtn, top);
        VBox.setMargin(removeRowBtn, Settings.verticalInsets);
        VBox.setMargin(addColumnBtn, top);
        VBox.setMargin(removeColumnBtn, Settings.verticalInsets);
        VBox.setMargin(emulateBtn, top);

        getChildren().add(generateBtn);
        getChildren().add(addRowBtn);
        getChildren().add(removeRowBtn);
        getChildren().add(addColumnBtn);
        getChildren().add(removeColumnBtn);
        getChildren().add(emulateBtn);

    }

    private void generateClick() {
        Table.instance.generate();
    }
    private void addRowClick() {
        Table.instance.addRow();
    }
    private void removeRowClick() {
        Table.instance.removeRow();
    }
    private void addColumnClick() {
        Table.instance.addColumn();
    }
    private void removeColumnClick() {
        Table.instance.removeColumn();
    }
    private void emulate() {
        Table.instance.emulate();
    }

}
