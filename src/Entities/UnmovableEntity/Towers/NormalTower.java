package entities.unmovableEntity.towers;

import entities.movableEntity.bullet.CircleBullet;
import game.Config;
import game.GameField;
import graphics.Sprite;

public class NormalTower extends Tower {
    public NormalTower(GameField gameField_) {
        super(gameField_);
        size_ = Config.TILE_SIZE;
        range_ = Config.NORMAL_TOWER_RANGE;
        speed_ = Config.NORMAL_TOWER_SPEED;
    }

    protected long tickDown = 0;

    @Override
    protected void fire() {

        if (tickDown-- > 0) return;
        if (target_ != null) {
            tickDown = (int) speed_;
            gameField_.addBullet(new CircleBullet(gameField_, xPos_, yPos_, target_));
        }

    }

    @Override
    public void draw() {
        super.draw();
        Sprite.normalTowerStage.draw(xPos_, yPos_);
        Sprite.normalTowerTop.drawRotatedImage(angleAim_, xPos_, yPos_);
    }
}
