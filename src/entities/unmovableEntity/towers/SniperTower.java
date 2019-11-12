package entities.unmovableEntity.towers;

import entities.movableEntity.bullet.SniperBullet;
import game.Config;
import game.GameField;
import graphics.Sprite;

public class SniperTower extends Tower {

    public SniperTower(GameField gameField_) {
        super(gameField_);
        size_ = Config.TILE_SIZE;
        range_ = Config.SNIPER_TOWER_RANGE;
        speed_ = Config.SNIPER_TOWER_SPEED;
        cost_ = Config.SNIPER_TOWER_COST;
    }

    private long tickDown = 0;

    @Override
    protected void fire() {
        {
            if (tickDown-- > 0) return;
            if (target_ != null) {
                tickDown = (int) speed_;
                gameField_.addBullet(new SniperBullet(gameField_, xPos_, yPos_, target_));
            }
        }
    }

    @Override
    public void draw() {
        super.draw();
        Sprite.sniperTowerStage.draw(xPos_, yPos_);
        Sprite.sniperTowerTop.drawRotatedImage(angleAim_, xPos_, yPos_);
    }
}
