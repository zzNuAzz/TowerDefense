package entities;


public abstract class GameEntity implements IRender {
    protected double xPos_ = 0;
    protected double yPos_ = 0;
    protected double size_ = 0;

    public void setPosition(double x, double y) {
        setXPosition(x);
        setYPosition(y);
    }

    public double getXPos() {
        return xPos_;
    }

    public double getYPos() {
        return yPos_;
    }

    public double getSize_() {
        return size_;
    }

    public void setXPosition(double x) {
        this.xPos_ = x;
    }

    public void setYPosition(double y) {
        this.yPos_ = y;
    }


}
