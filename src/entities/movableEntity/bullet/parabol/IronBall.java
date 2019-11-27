package entities.movableEntity.bullet.parabol;

import entities.movableEntity.enemies.Enemy;
import game.Config;
import game.GameField;
import graphics.Sprite;


public class IronBall extends ParabolBullet {

    public IronBall(GameField gameField, double xPos, double yPos, Enemy target_, int level) {
        super(gameField, xPos, yPos, target_, Config.IRON_BALL_SPEED, Config.IRON_BALL_STRENGTH[level]);
        sizeX_ = Sprite.ironBall.getWidth();
        sizeY_ = Sprite.ironBall.getHeight();
    }


    @Override
    public void draw() {
        Sprite.ironBall.draw(xPos_ - 32, yPos_);

    }
}
