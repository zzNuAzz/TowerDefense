package Graphics;

import javafx.scene.image.Image;

public class SpriteSheet {
    private String path_;
    private int size_;
    public static Image[][] images;
    SpriteSheet(String path, int size) {
        this.size_= size;
        this.path_ = path;
        loadSheet(path);
    }
    public void loadSheet(String path) {
        Image img = new Image(path);


    }
}
