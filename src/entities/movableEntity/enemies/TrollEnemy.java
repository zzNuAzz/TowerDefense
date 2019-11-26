package entities.movableEntity.enemies;

import com.sun.javafx.geom.Vec2d;
import game.Config;
import game.GameField;
import graphics.Sprite;

import java.util.Queue;

public class TrollEnemy extends Enemy {
    public TrollEnemy(GameField gameField, Queue<Vec2d> path) {
        super(gameField, path);
        sizeX_ = 91;
        sizeY_ = 64;
        init(Config.BOSS_ENEMY_MAX_HEALTH, Config.BOSS_ENEMY_SPEED, Config.BOSS_ENEMY_ARMOR, Config.BOSS_ENEMY_REWARD_COINS, Config.BOSS_ENEMY_HP_BAR_OFFSET);
        sprite = Sprite.troll;
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
