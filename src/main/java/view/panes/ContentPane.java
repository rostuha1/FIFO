package view.panes;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class ContentPane extends HBox {

    public static final ContentPane instance = new ContentPane();

    private ContentPane() {
        setMaxSize(0, 0);
        setAlignment(Pos.CENTER);

        HBox.setMargin(SideMenu.instance, Settings.horizontalInsets);
        HBox.setMargin(Table.instance, Settings.horizontalInsets);

        getChildren().add(SideMenu.instance);
        getChildren().add(Table.instance);
    }

}
