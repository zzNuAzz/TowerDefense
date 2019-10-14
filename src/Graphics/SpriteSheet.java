package Graphics;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class SpriteSheet {
    //private String path_;
    private int size_;
    private ArrayList<ArrayList<Image>> images = new ArrayList<>();
    private Image img;
    public static SpriteSheet texture = new SpriteSheet("res/Texture/texture1.png", 128);

    public int getSize_() {
        return size_;
    }

    public SpriteSheet(String path, int size) {
        this.size_ = size;
        loadSheet(path);
    }

    private void loadSheet(String path) {
        //path_ = path;
        Image img = null;
        try {
            img = new Image(path);
        } catch (Exception e) {
            System.err.println(e + " : " + path);
            return;
        }
        PixelReader pr = img.getPixelReader();
        PixelWriter pw = null;
        int row = (int) (img.getHeight() / size_);
        int col = (int) (img.getWidth() / size_);
        for (int offsetY = 0; offsetY < img.getHeight(); offsetY += size_) {
            ArrayList<Image> rowSprite = new ArrayList<>();
            for (int offsetX = 0; offsetX < img.getWidth(); offsetX += size_) {
                WritableImage writableImage = new WritableImage(size_, size_);
                pw = writableImage.getPixelWriter();
                for (int readX = 0; readX < size_; readX++) {
                    for (int readY = 0; readY < size_; readY++) {
                        Color color = pr.getColor(readX + offsetX, readY + offsetY);
                        pw.setColor(readX, readY, color);
                    }
                }
                rowSprite.add(writableImage);
            }
            images.add(rowSprite);
        }

    }

    public Sprite getSprite(int i, int j) {
        if (i >= images.size()) return null;
        if (j >= images.get(0).size()) return null;
        return new Sprite(images.get(i).get(j));
    }

    public Sprite getSprite(int i, int j, int w, int h) {
        WritableImage writableImage = new WritableImage(w * size_, h * size_);
        PixelWriter pw = writableImage.getPixelWriter();
        for (int row = 0; row < w; row++) {
            for (int col = 0; col < h; col++) {
                PixelReader pr = images.get(row + i).get(col + j).getPixelReader();
                for (int readX = 0; readX < size_; readX++) {
                    for (int readY = 0; readY < size_; readY++) {
                        Color color = pr.getColor(readX, readY);
                        pw.setColor(readX + col * size_, readY + row * size_, color);
                    }
                }
            }
        }
        return new Sprite(writableImage);
    }

}
