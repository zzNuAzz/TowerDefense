package entities.unmovableEntity.towers;

import entities.movableEntity.bullet.MachineGunBullet;
import game.Config;
import game.GameField;
import graphics.Sprite;
import org.omg.CORBA.CODESET_INCOMPATIBLE;

public class MachineGunTower extends Tower {
    public MachineGunTower(GameField gameField_) {
        super(gameField_);
        size_ = Config.TILE_SIZE;
        range_ = Config.MACHINE_GUN_TOWER_RANGE;
        speed_ = Config.MACHINE_GUN_TOWER_SPEED;
        cost_ = Config.MACHINE_GUN_TOWER_COST;
    }

    protected long tickDown1 = 0;
    protected long tickDown2 = 0;
    @Override
    protected void fire() {
        if(--tickDown1 > 0 && --tickDown2 > 0) return;
        if(tickDown1 <= 0) {
            if (target_ != null) {
                tickDown1 = (int) speed_;
                gameField_.addBullet(new MachineGunBullet(gameField_, xPos_ + 10 * Math.sin(angleAim_), yPos_ + 10 * Math.cos(angleAim_), target_));
            }
        }
        if(tickDown2 <= 0) {
            if (target_ != null) {
                tickDown2 = (int) speed_;
                gameField_.addBullet(new MachineGunBullet(gameField_, xPos_- 10 * Math.sin(angleAim_), yPos_ - 10 * Math.cos(angleAim_), target_));
            }
        }
    }

    @Override
    public void draw() {
        super.draw();
        Sprite.machineGunTowerStage.draw(xPos_, yPos_);
        Sprite.machineGunTowerTop.drawRotatedImage(angleAim_, xPos_, yPos_);
    }
}
