package entities.movableEntity.enemies;

import com.sun.javafx.geom.Vec2d;
import entities.Animation;
import entities.Status;
import entities.movableEntity.MovableEntity;
import game.Config;
import game.GameField;
import graphics.Sprite;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Enemy extends MovableEntity implements Animation {
    protected double healthPoint_;
    protected double ftHP;
    protected double speed_;
    protected double armor_;
    protected int rewardCoins_;
    protected GameField gameField_;

    protected Direction direct;
    protected Status status;

    Vec2d checkPoint;
    Queue<Vec2d> path = new LinkedList<>();

    private long createdTick;
    private double distanceSpawn;

    private double maxHP;
    private Vec2d hpBarOffset;

    protected int frame_ = 0;

    protected Sprite[][] sprite;
    private double damage;

    public Enemy(GameField gameField, Queue<Vec2d> path) {
        this.gameField_ = gameField;
        while (!path.isEmpty()) this.path.add(new Vec2d(path.poll()));
        checkPoint = this.path.poll();
        if (checkPoint != null) {
            setPosition(checkPoint.x, checkPoint.y);
        }
        direct = (Math.random() < 0.5 ? Direction.LEFT : Direction.RIGHT);
        status = Status.RUN;

    }

    public double getFutureHP() {
        return ftHP;
    }

    public double getHealthPoint() {
        return healthPoint_;
    }

    protected final void init(double healthPoint, double speed, double armor, int rewardCoins, Vec2d hpBarOffset) {
        this.maxHP = healthPoint;
        this.ftHP = healthPoint;
        this.healthPoint_ = healthPoint;
        this.speed_ = speed;
        this.armor_ = armor;
        this.rewardCoins_ = rewardCoins;
        this.hpBarOffset = hpBarOffset;
    }


    public double getDistanceSpawn() {
        return distanceSpawn;
    }

    public void setCreatedTick(long createdTick) {
        this.createdTick = createdTick;
    }


    private void move() {
        if (path.isEmpty()) {
            xVelocity_ = 0;
            yVelocity_ = 0;
            return;
        }
        Vec2d nextPoint = path.peek();
        if ((checkPoint.x <= nextPoint.x && xPos_ >= nextPoint.x) || (nextPoint.x <= checkPoint.x && nextPoint.x >= xPos_)) {
            if (!(checkPoint.x == nextPoint.x && yPos_ >= Math.min(checkPoint.y, nextPoint.y) && yPos_ <= Math.max(checkPoint.y, nextPoint.y))) {
                checkPoint.set(xPos_, yPos_);
                path.poll();
                if (path.isEmpty()) return;
                nextPoint = path.peek();
            }
        } else if ((checkPoint.y <= nextPoint.y && yPos_ >= nextPoint.y) || (nextPoint.y <= checkPoint.y && nextPoint.y >= yPos_)) {
            if (!(checkPoint.y == nextPoint.y && xPos_ >= Math.min(checkPoint.x, nextPoint.x) && xPos_ <= Math.max(checkPoint.x, nextPoint.x))) {
                checkPoint.set(xPos_, yPos_);
                path.poll();
                if (path.isEmpty()) return;
                nextPoint = path.peek();
            }
        }

        xVelocity_ = (nextPoint.x - checkPoint.x) / Vec2d.distance(checkPoint.x, checkPoint.y, nextPoint.x, nextPoint.y) * speed_;
        yVelocity_ = (nextPoint.y - checkPoint.y) / Vec2d.distance(checkPoint.x, checkPoint.y, nextPoint.x, nextPoint.y) * speed_;


        if (xVelocity_ > 0) direct = Direction.RIGHT;
        else if (xVelocity_ < 0) direct = Direction.LEFT;
    }

    public int getRewardCoins() {
        return rewardCoins_;
    }

    public void damage(double damage) {
        double realDamage = damage - armor_;
        healthPoint_ -= realDamage > 0 ? realDamage : 0;
        if (healthPoint_ <= 0) {
            status = Status.DIE;
            frame_ = 1;
        }
    }

    public void evaluateHP(double damage) {
        double realDamage = damage - armor_;
        ftHP -= realDamage > 0 ? realDamage : 0;
    }

    private boolean isOnDest() {
        return path.size() == 0;
    }

    @Override
    public void update(long t) {
        updateFrame(t);
        //on destination
        if (isOnDest() || status == Status.REMOVE) {
            if (isOnDest()) gameField_.damage(1);
            gameField_.deleteEntity(this);
        }
        if (status == Status.DIE) {
            if (frame_ == 0) status = Status.REMOVE;
            return;
        }

        move();
        super.update(t);
        distanceSpawn = (t - createdTick) * speed_;
    }

    public void drawHpBar() {
        if (healthPoint_ < maxHP && healthPoint_ > 0) {
            double percentHP = healthPoint_ / maxHP;
            Sprite.getGC().setFill(Color.WHITE);
            if (percentHP > 0.65) Sprite.getGC().setFill(Color.GREEN);
            else if (percentHP > 0.3) Sprite.getGC().setFill(Color.OLIVE);
            else Sprite.getGC().setFill(Color.RED);
            Sprite.getGC().fillRect(xPos_ + hpBarOffset.x + 1, yPos_ + hpBarOffset.y, (Config.HP_BAR_WIDTH) * percentHP, Config.HP_BAR_HEIGHT);
            Sprite.getGC().setStroke(Color.BLACK);
            Sprite.getGC().strokeRect(xPos_ + hpBarOffset.x, yPos_ + hpBarOffset.y, Config.HP_BAR_WIDTH, Config.HP_BAR_HEIGHT);
        }
    }

    @Override
    public void draw() {
        if (direct == Direction.LEFT)
            sprite[status == Status.DIE ? 2 : 0][frame_].draw(xPos_, yPos_);
        else if (direct == Direction.RIGHT)
            sprite[status == Status.DIE ? 3 : 1][frame_].draw(xPos_, yPos_);

    }
}
