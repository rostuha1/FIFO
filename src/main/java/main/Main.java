package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.panes.ContentPane;
import view.animation.AnimatedCircles;
import view.panes.Table;

public class Main extends Application {

    private static Pane wrapper = new Pane();
    private static BorderPane root = new BorderPane();
    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {

        wrapper.setPrefSize(WindowSettings.width, WindowSettings.height);
        root.setPrefSize(WindowSettings.width, WindowSettings.height);
        wrapper.getChildren().add(root);
        scene = new Scene(wrapper);
        scene.setFill(Color.TRANSPARENT);

        primaryStage.setFullScreen(WindowSettings.fullscreen);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);

        appInit(primaryStage);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void appInit(Stage primaryStage) {

        root.setStyle("-fx-background-color: rgb(20, 25, 15)");
        AnimatedCircles.createSpawnNodes(root);

        root.setCenter(ContentPane.instance);

    }

    public static Scene getScene() {
        return scene;
    }

    public static Pane getRoot() {
        return root;
    }

    public static Pane getWrapper() {
        return wrapper;
    }
}
