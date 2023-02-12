package main;

import game.Config;
import gui.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;
import sound.Sound;

public class Main extends Application{
    public static void main(String[] args) {
        Application.launch(args);
    }

    public static Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("Tower Defense");
        stage.setResizable(false);
        stage.setMaxWidth(Config.SCREEN_WIDTH);
        stage.setMaxHeight(Config.SCREEN_HEIGHT + 32/*Title Bar*/);
        stage.setScene(MainMenu.scene);

//        Sound.backGroundSound.play();
        stage.show();
    }
}
