package game;

import graphics.Sprite;
import gui.MainMenu;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import main.Main;


public class TowerDefense {

    public static final Group defeat = initDefeat();
    public static final Group victory = initVictory();

    public static final Scene scene = InitScene();


    private static Scene InitScene() {
        AnchorPane root = new AnchorPane();
        //canvas + graphics
        Canvas canvas = new Canvas(Config.CANVAS_WIDTH, Config.CANVAS_HEIGHT);
        Sprite.setGraphicsContext(canvas.getGraphicsContext2D());

        root.setOnMouseMoved(GameController::mouseMoved);
        root.setOnMouseClicked(GameController::mouseClicked);

//        canvas.setOnMouseClicked(e -> {
//            System.out.print(String.format("%d %d -> ", (int)e.getX() / 16, (int)e.getY() / 16));
//        });

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

        root.getChildren().addAll(canvas, gridButton, defeat, victory);
        return new Scene(root, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
    }

    private static Group initDefeat() {
        Group group = new Group();
        group.setVisible(false);
        ImageView back = new ImageView(new Image("res/gui/defeat.png"));
        back.setTranslateY(100);
        ImageView reset = new ImageView("res/gui/reset.png");
        reset.setTranslateX(550);
        reset.setTranslateY(550);
        reset.setOnMouseClicked(e -> {
            Main.stage.setScene(TowerDefense.scene);
            GameController.getInstance().newGame(MainMenu.level[MainMenu.room]);
            Sprite.background = Sprite.room[MainMenu.room];
            GameController.getInstance().start();
            group.setVisible(false);
        });

        ImageView menu = new ImageView("res/gui/menu.png");
        menu.setTranslateX(232);
        menu.setTranslateY(550);
        menu.setOnMouseClicked(e -> {
            Main.stage.setScene(MainMenu.scene);
            group.setVisible(false);
        });

        group.getChildren().addAll(back, reset, menu);
        return group;
    }

    private static Group initVictory() {
        Group group = new Group();
        group.setVisible(false);

        ImageView back = new ImageView(new Image("res/gui/victory.png"));
        back.setTranslateY(100);
        back.setTranslateX(100);
        ImageView menu = new ImageView("res/gui/menu.png");
        menu.setTranslateX(400);
        menu.setTranslateY(550);
        menu.setOnMouseClicked(e -> {
            group.setVisible(false);
            Main.stage.setScene(MainMenu.scene);
        });
        group.getChildren().addAll(back, menu);
        return group;
    }


}
