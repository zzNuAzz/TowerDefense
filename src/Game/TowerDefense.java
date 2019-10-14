package Game;

import Entities.MovableEntity.Enemy;
import Entities.MovableEntity.NormalEnemy;
import Graphics.Coordinate;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class TowerDefense extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private static double zoomX = 0.5;
    private static double zoomY = 0.5;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tower Defense");
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        Canvas canvas = new Canvas(128*20*zoomX, 128*12*zoomY);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.scale(zoomX,zoomY);
        System.out.println("canvas.getWidth() = " + canvas.getWidth());
//test class + method :)
        GameField gameField = new GameField("Level/level1.txt", 0);
        long time = System.nanoTime();
        new AnimationTimer(){
            @Override
            public void handle(long now) {
                gameField.update(now-time);
                gameField.draw(gc);
            }
        }.start();
        primaryStage.show();
    }
}
