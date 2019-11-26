package entities.unmovableEntity.towers;

import com.sun.javafx.geom.Vec2d;
import entities.Saleable;
import entities.Upgradeable;
import entities.movableEntity.enemies.Enemy;
import entities.unmovableEntity.GameTile;
import game.Config;
import game.GameField;
import graphics.Sprite;
import myUtils.Coordinate;
import javafx.scene.paint.Color;
import sound.Sound;

public abstract class Tower extends GameTile implements Upgradeable, Saleable {
    protected double[] range_;
    protected int[] speed_;
    protected int[] cost_;

    protected int level_;

    protected double angleAim_ = 0;
    protected Enemy target_;
    protected GameField gameField_;


    private void chooseTarget() {
        double x1 = xPos_ + sizeX_ / 2.;
        double y1 = yPos_ + sizeY_ / 2.;
        if (target_ != null && Vec2d.distance(x1, y1, target_.getXPos() + target_.getSizeX_() / 2, target_.getYPos() + target_.getSizeY_() / 2) > range_[level_])
            //untarget enemy
            if (target_ != null && Vec2d.distance(x1, y1, target_.getXPos() + target_.getSizeX_() / 2, target_.getYPos() + target_.getSizeY_() / 2) > range_[level_])
                target_ = null;
        if (target_ != null && target_.getFutureHP() <= 0) target_ = null;

        //select enemy
        if (target_ == null && gameField_.getEnemyList().size() > 0) {
            for (Enemy enemy : gameField_.getEnemyList()) {
                if (Vec2d.distance(x1, y1, enemy.getXPos() + enemy.getSizeX_() / 2, enemy.getYPos() + enemy.getSizeY_() / 2) < range_[level_] && enemy.getFutureHP() > 0) {
                    if (target_ == null) target_ = enemy;
                    else if (target_.getDistanceSpawn() < enemy.getDistanceSpawn()) target_ = enemy;
                }
            }
        }
        //turn to the enemy target
        if (target_ != null) rotate();
    }


    protected abstract void fire();

    @Override
    public void update(long t) {
        chooseTarget();
        fire();
    }

    public void drawRange() {
        Sprite.attack_ring.scale(2 * range_[level_], 2 * range_[level_]).draw(xPos_ + Config.TILE_SIZE / 2. - range_[level_], yPos_ + Config.TILE_SIZE / 2. - range_[level_]);
    }

    @Override
    public void draw() {
//        if (target_ != null) {
//            Sprite.getGC().setFill(Color.BLACK);
//            Sprite.getGC().strokeLine(xPos_ + Config.TILE_SIZE / 2., yPos_ + Config.TILE_SIZE / 2., target_.getXPos() + target_.getSizeX_() / 2, target_.getYPos() + target_.getSizeY_() / 2);
//        }
    }

    public Tower(GameField gameField) {
        xPos_ = -Config.CANVAS_WIDTH;
        yPos_ = -Config.CANVAS_HEIGHT;
        this.gameField_ = gameField;
        level_ = 0;
    }

    public void rotate() {
        double x1 = xPos_ + sizeX_ / 2;
        double y1 = yPos_ + sizeY_ / 2;
        double x2 = target_.getXPos() + target_.getSizeX_() / 2;
        double y2 = target_.getYPos() + target_.getSizeY_() / 2;
        angleAim_ = Coordinate.angle(x1, x2, y1, y2);
    }

    public boolean canPlace(int coins) {
        if (coins < cost_[level_]) return false;
        int x = (int) Coordinate.fixAccuracy(this.getXPos() / (Config.TILE_SIZE / 4.));
        int y = (int) Coordinate.fixAccuracy(this.getYPos() / (Config.TILE_SIZE / 4.));
        if (x < 0 || y < 0 || x + 3 > Config.CANVAS_WIDTH / (Config.TILE_SIZE / 4.) || y + 3 > Config.CANVAS_HEIGHT / (Config.TILE_SIZE / 4.))
            return false;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (gameField_.getMappingGameTile(x + j, y + i) != null) return false;
        return true;
    }

    public void mapping(GameField gameField, int x, int y) {
//        mapping(gameField, this, x, y, 4, 0, 0, 0, 0);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                gameField.mappingGameTile(x + j, y + i, this);
            }
        }
    }

    private void unMapping() {
        int x = (int) xPos_ / 16;
        int y = (int) yPos_ / 16;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                gameField_.mappingGameTile(x + j, y + i, null);
            }
        }
//        mapping(gameField_, null, ((int) (xPos_ / 16)), ((int) (yPos_ / 16)), 4, 0, 0, 0, 0);
    }

    @Override
    public int getUpCost() {
        return level_ + 1 < 3 ? cost_[level_ + 1] : Integer.MAX_VALUE;
    }

    public int getCostLV(int level_) {
        return cost_[level_];
    }

    @Override
    public void sell() {
        gameField_.deleteTile(this);
        gameField_.addCoin(getSell());
        unMapping();
    }

    @Override
    public boolean canUpgrade() {
        return level_ < 2;
    }

    @Override
    public void upgrade() {
        if (level_ >= 2) return;
        if (gameField_.getCoins() < cost_[level_ + 1]) return;
        level_++;
        gameField_.addCoin(-cost_[level_]);
    }

    @Override
    public int getSell() {
        int value = 0;
        for (int i = 0; i <= level_; i++) value += cost_[i] / 2;
        return value;
    }
}
