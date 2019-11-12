package entities.movableEntity.enemies;

import entities.GameEntity;
import graphics.Coordinate;

public abstract class MovableEntity extends GameEntity{
    protected double xVelocity_ = 0;
    protected double yVelocity_ = 0;

    public void setVelocity(double x, double y) {
        setXVelocity(x);
        setYVelocity(y);
    }

    public void setXVelocity(double xVelocity) {
        this.xVelocity_ = xVelocity;
    }

    public void setYVelocity(double yVelocity) {
        this.yVelocity_ = yVelocity;
    }

    @Override
    public void update(long t) {
        xPos_ = Coordinate.fixAccuracy(xPos_ + xVelocity_);
        yPos_ = Coordinate.fixAccuracy(yPos_ + yVelocity_);
    }
}
