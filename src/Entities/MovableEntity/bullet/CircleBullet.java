package entities.movableEntity.bullet;

import entities.movableEntity.enemies.Enemy;
import game.Config;
import game.GameField;
import graphics.Sprite;

public final class CircleBullet extends Bullet {

    public CircleBullet(GameField gameField, double xPos, double yPos, Enemy target) {
        super(gameField, target, Config.NORMAL_BULLET_SPEED, Config.NORMAL_BULLET_STRENGTH);
        setPosition(xPos, yPos);
        size_ = Config.TILE_SIZE;
    }

    @Override
    public void draw() {
        Sprite.normalBullet.draw(xPos_, yPos_);
    }
}
