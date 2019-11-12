package graphics;

import game.Config;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class SpriteSheet {
    private int size_;
    private ArrayList<ArrayList<Image>> images = new ArrayList<>();
    public static SpriteSheet texture_small = new SpriteSheet("res/Texture/texture_small.png", Config.TILE_SIZE);
    public static SpriteSheet getTexture_large = new SpriteSheet("res/Texture/texture_small.png", Config.LARGE_TILE_SIZE);

    public int getSize_() {
        return size_;
    }

    public SpriteSheet(String path, int size) {
        this.size_ = size;
        loadSheet(path);
    }

    private void loadSheet(String path) {
        Image img = null;
        try {
            img = new Image(path);
        } catch (Exception e) {
            System.err.println(e + ": " + path);
            return;
        }

        ImageView imageView = new ImageView(img);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);

        int row = (int) (img.getHeight() / size_);
        int col = (int) (img.getWidth() / size_);
        for (int i = 0; i < row; i++) {
            ArrayList<Image> rowSprite = new ArrayList<>();
            for (int j = 0; j < col; j++) {
                parameters.setViewport(new Rectangle2D(j * size_, i * size_, size_, size_));
                rowSprite.add(imageView.snapshot(parameters, null));
            }
            images.add(rowSprite);
        }
    }

    public Sprite getSprite(int i, int j) {
        if (i >= images.size()) return null;
        if (j >= images.get(0).size()) return null;
        return new Sprite(images.get(i).get(j));
    }

}
