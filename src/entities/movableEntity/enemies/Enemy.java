package entities.movableEntity.enemies;

import com.sun.javafx.geom.Vec2d;
import game.Config;
import game.GameField;
import graphics.Coordinate;
import graphics.GCSingleton;
import javafx.scene.paint.Color;

public abstract class Enemy extends MovableEntity {
    protected double healthPoint_;
    protected double speed_;
    protected double armor_;
    protected int rewardCoins_;
    protected GameField gameField_;
    protected Direct direct = Direct.RIGHT;

    private double maxHP;
    private Vec2d hpBarOffset;

    public enum Direct {DOWN, LEFT, RIGHT, UP}

    public Enemy(GameField gameField) {
        this.gameField_ = gameField;
    }

    public double getHealthPoint() {
        return healthPoint_;
    }

    protected final void init(double healthPoint_, double speed_, double armor_, int rewardCoins_, Vec2d hpBarOffset) {
        this.maxHP = healthPoint_;
        this.healthPoint_ = healthPoint_;
        this.speed_ = speed_;
        this.armor_ = armor_;
        this.rewardCoins_ = rewardCoins_;
        this.hpBarOffset = hpBarOffset;
    }

    public GameField getGameField_() {
        return gameField_;
    }

    public void setDirect(Direct direct) {
        this.direct = direct;
    }


    public Direct findDirect() {
        int j = (int) (xPos_ / Config.TILE_SIZE);
        int i = (int) (yPos_ / Config.TILE_SIZE);
        double dx = Coordinate.fixAccuracy(xPos_ % Config.TILE_SIZE);
        double dy = Coordinate.fixAccuracy(yPos_ % Config.TILE_SIZE);
        if (direct == Direct.RIGHT) {
            if (gameField_.getTiles(i, j + 1) == 4 && dx == 0) return Direct.DOWN;
            if (gameField_.getTiles(i + 1, j + 1) == 9 && dx == 0) return Direct.UP;
            return Direct.RIGHT;
        }
        if (direct == Direct.DOWN) {
            if (gameField_.getTiles(i + 1, j) == 8 && dy == 0) return Direct.RIGHT;
            if (gameField_.getTiles(i + 1, j + 1) == 9 && dy == 0) return Direct.LEFT;
            return Direct.DOWN;
        }
        if (direct == Direct.LEFT) {
            if (gameField_.getTiles(i + 1, j) == 8 && dx == 0) return Direct.UP;
            if (gameField_.getTiles(i, j) == 3 && dx == 0) return Direct.DOWN;
            return Direct.LEFT;
        }
        //direct == Direct.UP
        if (gameField_.getTiles(i, j + 1) == 4 && dy == 0) return Direct.LEFT;
        if (gameField_.getTiles(i, j) == 3 && dy == 0) return Direct.RIGHT;
        return Direct.UP;
    }

    public void damage(double damage) {
        double realDamage = damage - armor_;
        healthPoint_ -= realDamage > 0 ? realDamage : 0;
    }

    private boolean isOnDest() {
        return xPos_ > Config.CANVAS_WIDTH;
    }


    @Override
    public void update(long t) {
        direct = findDirect();
        switch (direct) {
            case RIGHT:
                setVelocity(speed_, 0);
                break;
            case LEFT:
                setVelocity(-speed_, 0);
                break;
            case UP:
                setVelocity(0, -speed_);
                break;
            case DOWN:
                setVelocity(0, speed_);
                break;
        }
        super.update(t);

        //on destination
        if (isOnDest() || healthPoint_ <= 0) {
            if (isOnDest()) gameField_.damage(1);
            gameField_.deleteEntity(this);
        }
    }

    public void drawHpBar() {

        if (healthPoint_ < maxHP) {
            GCSingleton.getInstance().setStroke(Color.BLACK);
            GCSingleton.getInstance().strokeRect(xPos_ + hpBarOffset.x, yPos_ + hpBarOffset.y, Config.HP_BAR_WIDTH, Config.HP_BAR_HEIGHT);
            double percentHP = healthPoint_ / maxHP;
            GCSingleton.getInstance().setFill(Color.WHITE);
            if (percentHP > 0.3) GCSingleton.getInstance().setFill(Color.GREEN);
            else GCSingleton.getInstance().setFill(Color.RED);
            GCSingleton.getInstance().fillRect(xPos_ + hpBarOffset.x + 1, yPos_ + hpBarOffset.y, (Config.HP_BAR_WIDTH - 2) * percentHP, Config.HP_BAR_HEIGHT - 2);
        }
    }

}
