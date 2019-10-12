package Entities;

import Graphics.Sprite;
import com.sun.javafx.geom.Vec2f;

public abstract class GameEntity {
    protected double xPos_ = 0;
    protected double yPos_ = 0;
    protected Sprite sprite_;

    public void setPosition(double x, double y) {
        setXPosition(x);
        setYPosition(y);
    }
    public void setPosition(Vec2f v) {
        setPosition(v.x, v.y);
    }
    public void setXPosition(double x) {
        this.xPos_ = x;
    }
    public void setYPosition(double y) {
        this.yPos_ = y;
    }
    public Vec2f getPosition() {
        return new Vec2f((float)xPos_, (float)yPos_);
    }
    public abstract void update(long t);
}
