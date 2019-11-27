package entities.movableEntity.bullet.normal;

import entities.movableEntity.enemies.Enemy;
import game.Config;
import game.GameField;
import graphics.Sprite;
import myUtils.Coordinate;

public class Arrow extends NormalBullet {
    private double angle_;

    public Arrow(GameField gameField, double xPos, double yPos, Enemy target_, int level) {
        super(gameField, xPos, yPos, target_, Config.ARROW_SPEED, Config.ARROW_STRENGTH[level]);
        sizeX_ = Config.TILE_SIZE;
    }

    @Override
    public void update(long t) {
        super.update(t);
        rotate();
    }

    @Override
    public void draw() {
        Sprite.arrow.drawRotatedImage(angle_, xPos_, yPos_);
    }

    public void rotate() {
        double x1 = xPos_ + sizeX_ / 2;
        double y1 = yPos_ + sizeY_ / 2;
        double x2 = target_.getXPos() + target_.getSizeX_() / 2;
        double y2 = target_.getYPos() + target_.getSizeY_() / 2;
        angle_ = Coordinate.angle(x1, x2, y1, y2);
    }
}
