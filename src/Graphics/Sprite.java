package Graphics;

import com.sun.javafx.geom.Vec2f;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
    private double width_;
    private double height_;
    private String path_;
    private Image image_;

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
    public Vec2f getSize() {
        return new Vec2f((float)width_, (float)height_);
    }
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
}
