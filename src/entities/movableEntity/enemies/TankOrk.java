package entities.movableEntity.enemies;

import com.sun.javafx.geom.Vec2d;
import game.Config;
import game.GameField;
import graphics.Sprite;

import java.util.Queue;

public class TankOrk extends Enemy {
    public TankOrk(GameField gameField, Queue<Vec2d> path) {
        super(gameField, path);
        sizeX_ = 43;
        sizeY_ = 32;
        init(Config.TANKER_ENEMY_MAX_HEALTH, Config.TANKER_ENEMY_SPEED, Config.TANKER_ENEMY_ARMOR, Config.TANKER_ENEMY_REWARD_COINS, Config.TANKER_ENEMY_HP_BAR_OFFSET);
        sprite = Sprite.tankOrk;
    }

    @Override
    public void updateFrame(long tick) {
        if(direct == Direction.LEFT || direct == Direction.RIGHT) {
            if(tick % 4 == 0) frame_ = (frame_ + 1) % 7;
        }else {
            if(tick % 5 == 0) frame_ = (frame_ + 1) % 7;
        }
    }
}
