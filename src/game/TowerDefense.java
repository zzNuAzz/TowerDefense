package game;

import graphics.Sprite;
import gui.MainMenu;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class TowerDefense{

    public static final Scene scene = InitScene();

    private static Scene InitScene() {
        AnchorPane root = new AnchorPane();
        //canvas + graphics
        Canvas canvas = new Canvas(Config.CANVAS_WIDTH, Config.CANVAS_HEIGHT);
        Sprite.setGraphicsContext(canvas.getGraphicsContext2D());

        root.setOnMouseMoved(GameController::mouseMoved);
        root.setOnMouseClicked(GameController::mouseClicked);

        canvas.setOnMouseClicked(e -> {
            System.out.print(String.format("%d %d -> ", (int)e.getX() / 16, (int)e.getY() / 16));
        });

        GridPane gridButton = new GridPane();
        AnchorPane.setLeftAnchor(gridButton, Config.CANVAS_WIDTH);
        gridButton.setPadding(new Insets(5, 5, 0, 5));
        gridButton.add(GameController.startButton, 0, 0);
        gridButton.add(GameController.stopButton, 1, 0);
        gridButton.add(GameController.nextWaveButton, 0, 1);
        gridButton.add(GameController.exitButton, 1, 1);

        gridButton.add(GameController.ironTowerButton, 0, 2, 2, 1);
        gridButton.add(GameController.fireTowerButton, 0, 3, 2, 1);
        gridButton.add(GameController.archerTowerButton, 0, 4, 2, 1);


//        gridButton.add(GameController.exp, 0,7);
//        gridButton.add(GameController.imp, 1,7);

        root.getChildren().addAll(canvas, gridButton);
        return new Scene(root, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
    }


}
