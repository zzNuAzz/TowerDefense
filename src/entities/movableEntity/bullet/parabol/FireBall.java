package entities.movableEntity.bullet.parabol;

import entities.movableEntity.enemies.Enemy;
import game.Config;
import game.GameField;
import graphics.Sprite;

public class FireBall extends ParabolBullet {
    private double angle_;

    public FireBall(GameField gameField, double xPos, double yPos, Enemy target_, int level) {
        super(gameField, xPos, yPos, target_, Config.FIRE_BALL_SPEED, Config.FIRE_BALL_STRENGTH[level]);
        setPosition(xPos, yPos);
        sizeX_ = Config.TILE_SIZE;
        sizeY_ = Config.TILE_SIZE;
    }

    @Override
    public void update(long t) {
        super.update(t);
        rotate();
    }

    @Override
    public void draw() {
        Sprite.fireBall.drawRotatedImage(angle_, xPos_ - 32, yPos_ - (angle_ == 0 ? 20 : 0));
    }

    public void rotate() {
        if (yVelocity_ + g_ * tickCount > 0) angle_ = 0;
        else angle_ = 180;
    }
}
