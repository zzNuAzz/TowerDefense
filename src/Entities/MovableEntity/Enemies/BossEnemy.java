package entities.movableEntity.Enemies;

import game.Config;
import game.GameField;
import graphics.Sprite;

public class BossEnemy extends Enemy {
    public BossEnemy(GameField gameField) {
        super(gameField);
        size_ = Config.TILE_SIZE * 2;
        init(Config.BOSS_ENEMY_MAX_HEALTH, Config.BOSS_ENEMY_SPEED, Config.BOSS_ENEMY_ARMOR, Config.BOSS_ENEMY_REWARD_COINS, Config.BOSS_ENEMY_HP_BAR_OFFSET);
    }

    @Override
    public void draw() {
        switch (direct) {
            case RIGHT:
                Sprite.bossEnemyRight.draw( xPos_, yPos_);
                break;
            case LEFT:
                Sprite.bossEnemyLeft.draw(xPos_, yPos_);
                break;
            case DOWN:
                Sprite.bossEnemyDown.draw(xPos_, yPos_);
                break;
            case UP:
                Sprite.bossEnemyUp.draw(xPos_, yPos_);
        }
    }
}
