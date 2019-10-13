package Graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class SpriteSheet {
    private String path_;
    private int size_;
    private ArrayList<ArrayList<Image>> images = new ArrayList<>();
    private Image img;

    public static SpriteSheet texture = new SpriteSheet("res/Texture/texture1.png", 128);

    public SpriteSheet(String path, int size) {
        this.size_= size;
        loadSheet(path);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(img, 100,100);
    }
    private void loadSheet(String path) {
        this.path_ = path;
        Image img = null;
        try {
            img =  new Image(path);
        }
        catch (Exception e) {
            System.err.println(e + " : " + path);
            return;
        }


        PixelReader pr = img.getPixelReader();
        PixelWriter pw = null;
//        System.out.println(img.getHeight());
//        System.out.println(img.getWidth());
//
//        System.out.println("img.getHeight()/size_ = " + img.getHeight() / size_);
//        System.out.println("img.getWidth() / size_ = " + img.getWidth() / size_);
        int row = (int)(img.getHeight() / size_);
        int col = (int)(img.getWidth() / size_);
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
        if(i >= images.size()) return  null;
        if(j >= images.get(0).size()) return  null;
        return new Sprite(images.get(i).get(j));
    }

}
