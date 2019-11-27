package entities.unmovableEntity.towers;

import entities.movableEntity.bullet.parabol.IronBall;
import game.Config;
import game.GameField;
import graphics.Sprite;

public class IronBallTower extends Tower {
    public IronBallTower(GameField gameField) {
        super(gameField);
        sizeX_ = Sprite.ironBallStage[0].getWidth();
        sizeY_ = Sprite.ironBallStage[0].getHeight();
        range_ = Config.IRON_TOWER_RANGE;
        speed_ = Config.IRON_TOWER_SPEED;
        cost_ = Config.IRON_TOWER_COST;
        tickDown = speed_[level_];
    }

    double offset = 0;
    private int tickDown ;

    @Override
    protected void fire() {
        if (target_ != null && tickDown-- <= 0) {
            tickDown = speed_[level_];
            gameField_.addBullet(new IronBall(gameField_, xPos_ + 32, yPos_, target_, level_));
        }
    }

    @Override
    public void update(long t) {
        super.update(t);
    }

    @Override
    public void draw() {
        super.draw();
        offset = 28 * (tickDown < 0.1 * speed_[level_] ? tickDown / (speed_[level_] * 0.1) : (speed_[level_] - tickDown) / (0.9 * speed_[level_]));
        if (offset < 0) offset = 0;

        Sprite.ironBallTowerUB[level_].draw(xPos_, yPos_ + offset);
        Sprite.ironBallStage[level_].draw(xPos_, yPos_);
        if (tickDown < 30) Sprite.ironBall.draw(xPos_ - 5, yPos_ - 24 + offset);
        Sprite.ironBallTowerUF[level_].draw(xPos_, yPos_ + Sprite.ironBallTowerUB[level_].getHeight() - (level_ == 2 ? 2 : 1) + offset);
    }
}
