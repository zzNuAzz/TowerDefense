package entities.unmovableEntity.towers;

import entities.movableEntity.bullet.normal.Arrow;
import entities.movableEntity.bullet.parabol.FireBall;
import entities.movableEntity.enemies.Direction;
import game.Config;
import game.GameField;
import graphics.Sprite;

public class ArcherTower extends Tower {
    public ArcherTower(GameField gameField) {
        super(gameField);
        sizeX_ = Sprite.archerTower[0][0].getWidth();
        sizeY_ = Sprite.archerTower[0][0].getHeight();
        range_ = Config.ARCHER_TOWER_RANGE;
        speed_ = Config.ARCHER_TOWER_SPEED;
        cost_ = Config.ARCHER_TOWER_COST;
        tickDown = speed_[level_];
    }

    Direction dir = Direction.RIGHT;
    private int tickDown;

    @Override
    protected void fire() {
        if (target_ != null && tickDown-- <= 0) {
            tickDown = speed_[level_];
            gameField_.addBullet(new Arrow(gameField_, xPos_ +16, yPos_+20, target_, level_));
        }
    }

    @Override
    public void update(long t) {
        super.update(t);
    }

    @Override
    public void draw() {
        super.draw();
        if (target_ != null)
            dir = (xPos_ + sizeX_ / 2 -target_.getXPos() - target_.getSizeX_() / 2) > 0 ? Direction.LEFT : Direction.RIGHT;
        Sprite.archerTower[dir == Direction.LEFT ? 0 : 1][level_].draw(xPos_, yPos_);
    }
}
