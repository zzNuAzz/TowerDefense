package entities.movableEntity.enemies;

import com.sun.javafx.geom.Vec2d;
import entities.Status;
import game.Config;
import game.GameField;
import graphics.Sprite;

import java.util.Queue;

public class BatEnemy extends Enemy {
    public BatEnemy(GameField gameField, Queue<Vec2d> path) {
        super(gameField, path);
        sizeX_ = 43;
        sizeY_ = 32;
        init(Config.FAST_ENEMY_MAX_HEALTH, Config.FAST_ENEMY_SPEED, Config.FAST_ENEMY_ARMOR, Config.FAST_ENEMY_REWARD_COINS, Config.FAST_ENEMY_HP_BAR_OFFSET);
        sprite = Sprite.batEnemy;
    }

    @Override
    public void updateFrame(long tick) {
        if(status == Status.RUN && (direct == Direction.LEFT || direct == Direction.RIGHT)) {
            frame_ = (frame_ + 1) % 25;
        }else {
            if(tick % 3 == 0) frame_ = (frame_ + 1) % 24;
        }
    }

}
