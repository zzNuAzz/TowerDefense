import Graphics.Sprite;
import Graphics.SpriteSheet;
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

//test class + method :)
        new AnimationTimer() {
            int x = 0;
            @Override
            public void handle(long now) {
                /*
                |================================================================
                |đoạn code này vẽ bản đồ thế thôi, update bản đồ mỗi 60 frame :3|
                |chứ đéo có nghĩa mẹ gì :3                                      |
                |================================================================
                */
                if(x++ % 60 == 0) {
                    GameField gameField = new GameField("Level/level1.txt", 5);
                    gameField.draw(gc);
                    gc.setFill(Color.AQUA);
                    for(int i = 0; i < 128*20; i+= 128 / 2)
                        gc.fillRect(i,0,1,128 * 12 );;
                    for(int i = 0; i < 128*12; i+= 128 / 2)
                        gc.fillRect(0,i,128 * 20,1);
                }
            }
        }.start();


        primaryStage.show();
    }
}
