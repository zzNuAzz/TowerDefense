package entities;


public abstract class GameEntity implements IRender {
    protected double xPos_ = 0;
    protected double yPos_ = 0;
    protected double sizeX_ = 0;
    protected double sizeY_ = 0;

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

    public double getSizeX_() {
        return sizeX_;
    }
    public double getSizeY_() {
        return sizeY_;
    }


    public void setXPosition(double x) {
        this.xPos_ = x;
    }

    public void setYPosition(double y) {
        this.yPos_ = y;
    }


}
