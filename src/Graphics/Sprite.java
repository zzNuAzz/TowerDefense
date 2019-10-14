package Graphics;

import com.sun.javafx.geom.Vec2f;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Sprite {
    private double width_;
    private double height_;
    //private String path_;
    private double realWidth_;
    private double realHeight_;
    private Image image_;

    public Sprite(){
    }
    public Sprite(String path) {
        //this.path_ = path;
        loadImage(path);
    }
    public Sprite(Image image) {
        setImage(image);
    }
    public Sprite(String path, double width, double height) {
        this(path);
        setSize(width, height);
        setRealSize(width, height);
    }
    public Sprite(String path, double width, double height, double realWidth, double realHeight) {
        this(path);
        setSize(width, height);
        setRealSize(realWidth, realHeight);
    }

    public void setSize(double width, double height) {
        this.width_ = width;
        this.height_ = height;
    }
    public void setRealSize(double realWidth, double realHeight) {
        this.realWidth_ = realWidth;
        this.realHeight_ = realHeight;
    }
    public Vec2f getSize() {
        return new Vec2f((float)width_, (float)height_);
    }
    public Vec2f getRealSize() { return new Vec2f((float)realWidth_, (float)realHeight_);}
    public double getWidth() {
        return width_;
    }
    public double getHeight() {
        return height_;
    }
    public void setImage(Image image, double realWidth_, double realHeight_) {
        image_ = image;
        setSize(image.getWidth(), image.getHeight());
        setRealSize(realWidth_,realHeight_);
    }
    public  void setImage(Image img) {
        setImage(img, img.getWidth(), img.getHeight());
    }
    public  void loadImage(String path) {
        //this.path_ = path;
        setImage(new Image(path));
    }

    public void draw(GraphicsContext gc, double x, double y) {
        gc.drawImage(image_, x, y);
    }

    /*
    |============================================================
    | Theme Sprite                                              |
    |============================================================
    */
    public static ArrayList<ArrayList<Sprite>> listTheme = getTheme();
    private static ArrayList<ArrayList<Sprite>> getTheme() {
        ArrayList<ArrayList<Sprite>> listTheme = new ArrayList<>();

        for (int offsetX = 0; offsetX < 3; offsetX++) {
            for (int offsetY = 0; offsetY < 4; offsetY++) {
                ArrayList<Sprite> theme = new ArrayList<>();
                for(int i = 0; i < 3; i++)
                    for(int j = 0; j < 5; j++)
                        theme.add(SpriteSheet.texture.getSprite(i+offsetY * 3,j+offsetX * 5));
                listTheme.add(theme);
            }
        }
        return listTheme;
    }
    /*
    |========================================================
    |Enemy Sprite
    |========================================================
    */
    public static Sprite normalEnemy = SpriteSheet.texture.getSprite(11,15,2,2);
}
