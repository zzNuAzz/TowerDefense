package Entities;

public abstract class MovableEntity extends GameEntity{
    protected double xVelocity_ = 0;
    protected double yVelocity_ = 0;

    public void setXVelocity(double xVelocity) {
        this.xVelocity_ = xVelocity;
    }
    public void setYVelocity(double yVelocity) {
        this.yVelocity_ = yVelocity;
    }
    public double getXVelocity() {
        return xVelocity_;
    }
    public double getYVelocity() {
        return yVelocity_;
    }

    @Override
    public void update(long t) {
        xPos_ += xVelocity_;
        yPos_ += yVelocity_;
    }
}
