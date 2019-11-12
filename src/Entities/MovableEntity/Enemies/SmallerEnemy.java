package entities.movableEntity.enemies;

import game.Config;
import game.GameField;
import graphics.Sprite;

public class SmallerEnemy extends Enemy {

    public SmallerEnemy(GameField gameField) {
        super(gameField);
        size_ = Config.TILE_SIZE * 2;
        init(Config.SMALLER_ENEMY_MAX_HEALTH, Config.SMALLER_ENEMY_SPEED, Config.SMALLER_ENEMY_ARMOR, Config.SMALLER_ENEMY_REWARD_COINS, Config.SMALLER_ENEMY_HP_BAR_OFFSET);
    }

    public void draw() {

        switch (direct) {
            case RIGHT:
                Sprite.smallerEnemyRight.draw(xPos_, yPos_);
                break;
            case LEFT:
                Sprite.smallerEnemyLeft.draw(xPos_, yPos_);
                break;
            case DOWN:
                Sprite.smallerEnemyDown.draw(xPos_, yPos_);
                break;
            case UP:
                Sprite.smallerEnemyUp.draw(xPos_, yPos_);
        }
    }
}
