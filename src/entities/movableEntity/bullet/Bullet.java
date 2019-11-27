package entities.movableEntity.bullet;

import entities.movableEntity.MovableEntity;
import entities.movableEntity.enemies.Enemy;
import game.GameField;

public abstract class Bullet extends MovableEntity {

    protected double dame;
    protected double speed_;

    protected Enemy target_;
    protected GameField gameField_;

    public Bullet(GameField gameField, Enemy target_, double speed, double dame) {
        this.gameField_ = gameField;
        this.dame = dame;
        this.target_ = target_;
        this.speed_ = speed;
        target_.evaluateHP(dame);
    }
}
