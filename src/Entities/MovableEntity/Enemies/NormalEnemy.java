package entities.movableEntity.enemies;

import game.Config;
import game.GameField;
import graphics.Sprite;

public class NormalEnemy extends Enemy {
    public NormalEnemy(GameField gameField) {
        super(gameField);
        size_ = Config.TILE_SIZE * 2;
        init(Config.NORMAL_ENEMY_MAX_HEALTH, Config.NORMAL_ENEMY_SPEED, Config.NORMAL_ENEMY_ARMOR, Config.NORMAL_ENEMY_REWARD_COINS, Config.NORMAL_ENEMY_HP_BAR_OFFSET);
    }

    @Override
    public void draw() {
        switch (direct) {
            case RIGHT:
                Sprite.normalEnemyRight.draw(xPos_, yPos_);
                break;
            case LEFT:
                Sprite.normalEnemyLeft.draw( xPos_, yPos_);
                break;
            case DOWN:
                Sprite.normalEnemyDown.draw(xPos_, yPos_);
                break;
            case UP:
                Sprite.normalEnemyUp.draw(xPos_, yPos_);
        }
    }
}

