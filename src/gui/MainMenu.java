package gui;

import game.Config;
import game.GameController;
import game.TowerDefense;
import graphics.Sprite;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Main;

public class MainMenu {
    public static final Scene scene = InitScene();
    private static final String[] level = {"Level/level1.data", "Level/level2.data", "Level/level3.data"};
    private static int room = 0;

    private static Scene InitScene() {

        Group root = new Group();
        Group chooseLevel = new Group();
        chooseLevel.setVisible(false);

        ImageView backGround = new ImageView(Sprite.mainMenuImg.getImage());

        ImageView start = new ImageView(Sprite.start.getImage());
        start.setTranslateX(500);
        start.setTranslateY(450);
        start.setOnMouseClicked(event -> {
            chooseLevel.setVisible(true);
        });

        ImageView viewChooseLV = new ImageView(Sprite.chooseLevel.getImage());
        viewChooseLV.setTranslateX(250);
        viewChooseLV.setTranslateY(150);

        ImageView back = new ImageView(Sprite.backtomenu.getImage());
        back.setTranslateX(240);
        back.setTranslateY(590);
        back.setOnMouseClicked(event -> {
            chooseLevel.setVisible(false);
        });

        ImageView roomView = new ImageView(Sprite.roomView[room].getImage());
        roomView.setTranslateX(525);
        roomView.setTranslateY(275);

        ImageView backward = new ImageView(Sprite.backward.getImage());
        backward.setTranslateX(100);
        backward.setTranslateY(330);
        backward.setOnMouseClicked(event -> {
            room = (room + 3 - 1) % 3;
            roomView.setImage(Sprite.roomView[room].getImage());
        });

        ImageView forward = new ImageView(Sprite.forward.getImage());
        forward.setTranslateX(1100);
        forward.setTranslateY(330);
        forward.setOnMouseClicked(event -> {
            room = (room + 3 + 1) % 3;
            roomView.setImage(Sprite.roomView[room].getImage());
        });
        ImageView tick = new ImageView(Sprite.tick.getImage());
        tick.setTranslateX(810);
        tick.setTranslateY(590);
        tick.setOnMouseClicked(event -> {
            Main.stage.setScene(TowerDefense.scene);
            GameController.getInstance().newGame(level[room]);
            Sprite.background = Sprite.room[room];
            GameController.getInstance().start();
            chooseLevel.setVisible(false);
        });

        chooseLevel.getChildren().addAll(viewChooseLV, roomView, back, tick, backward, forward);
        root.getChildren().addAll(backGround, start, chooseLevel);
        return new Scene(root, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
    }


}
