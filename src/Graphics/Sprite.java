package Graphics;

import com.sun.javafx.geom.Vec2f;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Sprite {
    private double width_;
    private double height_;
    private String path_;
    private Image image_;
    private double realWidth_;
    private double realHeight_;

    public Sprite(){
    }
    public Sprite(String path) {
        this.path_ = path;
        loadImage(path);
    }
    public Sprite(String path, double width, double height) {
        this(path);
        setSize(width, height);
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
    public void setImage(Image i) {
        image_ = i;
        setSize(i.getWidth(), i.getHeight());
    }
    public  void loadImage(String path) {
        this.path_ = path;
        setImage(new Image(path));
    }
    public void draw(GraphicsContext gc, double x, double y) {
        gc.drawImage(image_, x, y);
    }
    /*
    |---------------------------------------------------------
    | Theme Sprite
    |---------------------------------------------------------
    */
    public static ArrayList<ArrayList<Sprite>> listTheme = getTheme();
    private static ArrayList<ArrayList<Sprite>> getTheme() {
        ArrayList<ArrayList<Sprite>> listTheme = new ArrayList<>();
        ArrayList<Sprite> theme = new ArrayList<>();
        for(int i = 1; i <= 15; i++)
            theme.add(new Sprite("res/TileMap/Theme1/theme"+i+".png"));
        listTheme.add(theme);
        return listTheme;
    }
}
