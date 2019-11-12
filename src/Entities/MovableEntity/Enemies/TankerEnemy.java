package entities.movableEntity.Enemies;

import game.Config;
import game.GameField;
import graphics.Sprite;

public class TankerEnemy extends Enemy {
    public TankerEnemy(GameField gameField) {
        super(gameField);
        size_ = Config.TILE_SIZE * 2;
        init(Config.TANKER_ENEMY_MAX_HEALTH, Config.TANKER_ENEMY_SPEED, Config.TANKER_ENEMY_ARMOR, Config.TANKER_ENEMY_REWARD_COINS, Config.TANKER_ENEMY_HP_BAR_OFFSET);
    }

    @Override
    public void draw() {
        switch (direct) {
            case RIGHT:
                Sprite.tankerEnemyRight.draw(xPos_, yPos_);
                break;
            case LEFT:
                Sprite.tankerEnemyLeft.draw(xPos_, yPos_);
                break;
            case DOWN:
                Sprite.tankerEnemyDown.draw( xPos_, yPos_);
                break;
            case UP:
                Sprite.tankerEnemyUp.draw(xPos_, yPos_);
        }
    }
}
