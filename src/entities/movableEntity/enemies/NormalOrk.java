package entities.movableEntity.enemies;

import com.sun.javafx.geom.Vec2d;
import game.Config;
import game.GameField;
import graphics.Sprite;

import java.util.Queue;

public class NormalOrk extends Enemy {
    public NormalOrk(GameField gameField, Queue<Vec2d> path) {
        super(gameField, path);
        sizeX_ = 43;
        sizeY_ = 32;
        init(Config.NORMAL_MAX_HEALTH, Config.NORMAL_SPEED, Config.NORMAL_ARMOR, Config.NORMAL_REWARD_COINS, Config.NORMAL_HP_BAR_OFFSET);
        sprite = Sprite.normalOrk;
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
