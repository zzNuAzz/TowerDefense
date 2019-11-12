package entities.movableEntity.bullet;

import com.sun.javafx.geom.Vec2d;
import entities.movableEntity.Enemies.Enemy;
import entities.movableEntity.Enemies.MovableEntity;
import game.GameField;

public abstract class Bullet extends MovableEntity {
    private double dame;
    private double speed_;

    protected Enemy target_;
    private GameField gameField_;

    public Bullet(GameField gameField, Enemy target_, double speed, double dame) {
        this.gameField_ = gameField;
        this.dame = dame;
        this.target_ = target_;
        this.speed_ = speed;
    }

    public void update(long t) {
        double x1 = xPos_+ size_ / 2;
        double y1 = yPos_ + size_ / 2;
        double x2 = target_.getXPos() + target_.getSize_() / 2;
        double y2 = target_.getYPos() + target_.getSize_() / 2;

        double d = Vec2d.distance(x1, y1, x2, y2);
        double dx = x2 - x1;
        double dy = y2 - y1;
        if (d >= speed_) {
            xVelocity_ = dx / d * speed_;
            yVelocity_ = dy / d * speed_;
        } else {
            xVelocity_ = dx;
            yVelocity_ = dy;
        }
        super.update(t);
        
        if (d < size_ / 10) {
            target_.damage(dame);
            gameField_.deleteBullet(this);
        }
    }

}
