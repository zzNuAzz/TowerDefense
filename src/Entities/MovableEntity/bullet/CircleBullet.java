package entities.movableEntity.bullet;

import com.sun.javafx.geom.Vec2d;
import entities.movableEntity.Enemies.Enemy;
import entities.unmovableEntity.Towers.Tower;
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
