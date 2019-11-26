package entities.movableEntity.bullet.parabol;

import entities.movableEntity.bullet.Bullet;
import entities.movableEntity.enemies.Enemy;
import game.GameField;

public abstract class ParabolBullet extends Bullet {
    public ParabolBullet(GameField gameField, double xPos, double yPos, Enemy target, double speed, double dame) {
        super(gameField,target,speed, dame);
        setPosition(xPos,yPos);
        x0 = xPos_;
        y0 = yPos_;
        target_ = target;
//        xVelocity_ = (target.getXPos() + target.getSizeX_() / 2d - xPos_ - sizeX_ / 2) / speed_;
//        yVelocity_ = (target.getYPos() + target.getSizeY_() / 2d - yPos_ - sizeY_ / 2) / speed_ - 1/2d * speed_ * g_;
    }

    private double x0, y0;
    protected double g_ = 1.5;
    protected int tickCount = 0;
    public void update(long t) {
        tickCount++;
        xVelocity_ = (target_.getXPos() + target_.getSizeX_() / 2d + 30 - x0 - sizeX_ / 2) / speed_;
        yVelocity_ = (target_.getYPos() + target_.getSizeY_() - y0 - sizeY_ / 2) / speed_ - 1/2d * speed_ * g_;

        xPos_ = x0 + xVelocity_ * tickCount;
        yPos_ = y0 + yVelocity_ * tickCount + 1d / 2d * g_ * tickCount * tickCount;

        if(tickCount >= speed_) {
            target_.damage(dame);
            gameField_.deleteBullet(this);
        }

    }

}
