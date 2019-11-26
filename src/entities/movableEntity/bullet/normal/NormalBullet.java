package entities.movableEntity.bullet.normal;

import com.sun.javafx.geom.Vec2d;
import entities.movableEntity.bullet.Bullet;
import entities.movableEntity.enemies.Enemy;
import game.GameField;

public abstract class NormalBullet extends Bullet {
    public NormalBullet(GameField gameField, double xPos, double yPos, Enemy target, double speed, long dame) {
        super(gameField,target, speed, dame);
        setPosition(xPos,yPos);
    }

    @Override
    public void update(long t) {
        double x1 = xPos_+ sizeX_ / 2;
        double y1 = yPos_ + sizeY_ / 2;
        double x2 = target_.getXPos() + target_.getSizeX_() / 2;
        double y2 = target_.getYPos() + target_.getSizeY_() / 2;

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

        if (d < speed_) {
            target_.damage(dame);
            gameField_.deleteBullet(this);
        }
    }
}
