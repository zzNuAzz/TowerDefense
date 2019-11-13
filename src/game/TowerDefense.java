package game;

import entities.unmovableEntity.GameTile;
import graphics.GCSingleton;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class TowerDefense extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tower Defense");
        primaryStage.setResizable(false);
        primaryStage.setMaxWidth(Config.SCREEN_WIDTH);
        primaryStage.setMaxHeight(Config.SCREEN_HEIGHT + 32/*Title Bar*/);

        AnchorPane root = new AnchorPane();
        primaryStage.setScene(new Scene(root, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));
        //canvas + graphics
        Canvas canvas = new Canvas(Config.CANVAS_WIDTH, Config.CANVAS_HEIGHT);
        root.setOnMouseMoved(GameController::mouseMoved);
        root.setOnMouseClicked(GameController::mouseClicked);
        GCSingleton.setInstance(canvas.getGraphicsContext2D());

        canvas.setOnMouseClicked(mouse -> {
            int x = (int) (mouse.getX() / (Config.TILE_SIZE / 4));
            int y = (int) (mouse.getY() / (Config.TILE_SIZE / 4));
            GameTile g = GameController.getInstance().gameField.getMappingGameTile(x, y);

        });

        GridPane gridButton = new GridPane();
        AnchorPane.setLeftAnchor(gridButton, Config.CANVAS_WIDTH);
        gridButton.setPadding(new Insets(5, 5, 0, 5));
        gridButton.add(GameController.startButton, 0, 0);
        gridButton.add(GameController.stopButton, 1, 0);
        gridButton.add(GameController.nextWaveButton, 0, 1,2,1);
        gridButton.add(GameController.normalTowerButton, 0, 2, 2, 1);
        gridButton.add(GameController.sniperTowerButton, 0, 3, 2, 1);
        gridButton.add(GameController.machineGunTowerButton, 0, 4, 2, 1);
        root.getChildren().addAll(canvas, gridButton);

        GameController.getInstance().start();
        primaryStage.show();
    }

}
