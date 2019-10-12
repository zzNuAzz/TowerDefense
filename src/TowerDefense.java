import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class TowerDefense extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tower Defense");
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        Canvas canvas = new Canvas(128*20 /2, 128*12 /2);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.scale(0.5,0.5);
        GameField gameField = new GameField("Level/level1.txt");
        gameField.draw(gc);

        gc.setStroke(Color.BLACK);
        for(int i = 0; i < 128*20; i+= 128)
            gc.fillRect(i,0,1,128 * 12);;
        for(int i = 0; i < 128*12; i+= 128)
            gc.fillRect(0,i,128 * 20,1);

        primaryStage.show();
    }
}
