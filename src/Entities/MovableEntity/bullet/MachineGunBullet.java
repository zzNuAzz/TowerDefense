package entities.movableEntity.bullet;

import entities.Rotatable;
import entities.movableEntity.enemies.Enemy;
import game.Config;
import game.GameField;
import graphics.Coordinate;
import graphics.Sprite;

public class MachineGunBullet extends Bullet implements Rotatable {
    private double angle_;

    public MachineGunBullet(GameField gameField, double xPos, double yPos, Enemy target_) {
        super(gameField, target_, Config.MACHINE_GUN_BULLET_SPEED, Config.MACHINE_GUN_BULLET_STRENGTH);
        setPosition(xPos, yPos);
        size_ = Config.TILE_SIZE;
    }

    @Override
    public void update(long t) {
        super.update(t);
        rotate();
    }

    @Override
    public void draw() {
        Sprite.machineGunBullet.drawRotatedImage(angle_, xPos_, yPos_);
    }

    @Override
    public void rotate() {
        double x1 = xPos_ + size_ / 2;
        double y1 = yPos_ + size_ / 2;
        double x2 = target_.getXPos() + target_.getSize_() / 2;
        double y2 = target_.getYPos() + target_.getSize_() / 2;
        angle_ = Coordinate.angle(x1, x2, y1, y2);
    }
}
