package sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Sound {
    public static MediaPlayer backGroundSound = initBackSound();

    private static MediaPlayer initBackSound() {
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("Sound/Track1.mp3").toURI().toString()));
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        return mediaPlayer;
    }
}