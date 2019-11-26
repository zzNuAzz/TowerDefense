package entities.unmovableEntity.towers;

import entities.movableEntity.bullet.parabol.FireBall;
import game.Config;
import game.GameField;
import graphics.Sprite;

public class FireBallTower extends Tower {
    public FireBallTower(GameField gameField) {
        super(gameField);
        sizeX_ = Sprite.fireBallStage[0].getWidth();
        sizeY_ = Sprite.fireBallStage[0].getHeight();
        range_ = Config.FIRE_BALL_TOWER_RANGE;
        speed_ = Config.FIRE_BALL_TOWER_SPEED;
        cost_ = Config.FIRE_BALL_TOWER_COST;
        tickDown = speed_[level_];
    }

    double offset = 0;
    private int tickDown;

    @Override
    protected void fire() {
        if (target_ != null && tickDown-- <= 0) {
            tickDown = speed_[level_];
            gameField_.addBullet(new FireBall(gameField_, xPos_ + 32, yPos_, target_, level_));
        }
    }

    @Override
    public void update(long t) {
        super.update(t);
    }

    @Override
    public void draw() {
        super.draw();
        offset = 20 * (tickDown < 0.1 * speed_[level_] ? tickDown / (speed_[level_] * 0.1) : (speed_[level_] - tickDown) / (0.9 * speed_[level_]));
        if (offset < 0) offset = 0;

        Sprite.fireBallTowerUB[level_].draw(xPos_, yPos_ + offset);
        Sprite.fireBallStage[level_].draw(xPos_, yPos_);
        if (tickDown < 30) Sprite.fireBall.drawRotatedImage(180, xPos_, yPos_ - 20 + offset);
        Sprite.fireBallTowerUF[level_].draw(xPos_, yPos_ + 6 + offset);
    }
}
