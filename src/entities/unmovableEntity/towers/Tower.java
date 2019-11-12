package entities.unmovableEntity.towers;

import com.sun.javafx.geom.Vec2d;
import entities.Rotatable;
import entities.movableEntity.enemies.Enemy;
import entities.unmovableEntity.GameTile;
import game.Config;
import game.GameField;
import graphics.Coordinate;
import graphics.GCSingleton;
import javafx.scene.paint.Color;

public abstract class Tower extends GameTile implements Rotatable {
    protected double range_;
    protected double speed_;
    protected double cost_;

    protected double angleAim_ = 0;
    protected Enemy target_;
    protected GameField gameField_;


    private void chooseTarget() {
        double x1 = xPos_ + size_ / 2.;
        double y1 = yPos_ + size_ / 2.;
        //untarget enemy
        if (target_ != null && Vec2d.distance(x1, y1, target_.getXPos() + target_.getSize_(), target_.getYPos() + target_.getSize_()) > range_)
            target_ = null;
        if (target_ != null && target_.getHealthPoint() <= 0) target_ = null;

        //select enemy
        if (target_ == null && gameField_.getEnemyList().size() > 0) {
            for (int i = gameField_.getEnemyList().size() - 1; i >= 0; i--) {
                Enemy enemy = gameField_.getEnemyList().get(i);
                if (Vec2d.distance(x1, y1, enemy.getXPos() + Config.TILE_SIZE, enemy.getYPos() + Config.TILE_SIZE) < range_)
                    if(target_ == null) target_ = enemy;
                    else if(target_.getDistanceSpawn() < enemy.getDistanceSpawn()) target_ = enemy;
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

    @Override
    public void draw() {
        GCSingleton.getInstance().setStroke(Color.GREEN);
        GCSingleton.getInstance().strokeOval(xPos_ + Config.TILE_SIZE / 2. - range_, yPos_ + Config.TILE_SIZE / 2. - range_, 2 * range_, 2 * range_);
//        if (target_ != null) {
//            GCSingleton.getInstance().setFill(Color.BLACK);
//            GCSingleton.getInstance().strokeLine(xPos_ + Config.TILE_SIZE / 2., yPos_ + Config.TILE_SIZE / 2., target_.getXPos() + Config.TILE_SIZE, target_.getYPos() + Config.TILE_SIZE);
//        }
    }

    public Tower(GameField gameField) {
        xPos_ = Config.CANVAS_WIDTH;
        yPos_ = Config.CANVAS_HEIGHT;
        this.gameField_ = gameField;
    }

    @Override
    public void rotate() {
        double x1 = xPos_ + size_ / 2;
        double y1 = yPos_ + size_ / 2;
        double x2 = target_.getXPos() + target_.getSize_() / 2;
        double y2 = target_.getYPos() + target_.getSize_() / 2;

        angleAim_ = Coordinate.angle(x1, x2, y1, y2);
    }

    public boolean canPlace(int coins) {
        if(coins < cost_) return false;
        int x = (int) Coordinate.fixAccuracy(this.getXPos() / (Config.TILE_SIZE / 4.));
        int y = (int) Coordinate.fixAccuracy(this.getYPos() / (Config.TILE_SIZE / 4.));
        if (x < 0 || y < 0 || x + 4 > Config.CANVAS_WIDTH / (Config.TILE_SIZE / 4.) || y + 4 > Config.CANVAS_HEIGHT / (Config.TILE_SIZE / 4.))
            return false;
        for(int i = 0; i < 4;i++)
            for(int j =0; j < 4;j++)
                if(gameField_.getMappingGameTile(x+j,y+i) != null) return false;
        return true;
    }
    public void mapping(GameField gameField, int x, int y) {
        mapping(gameField, x, y, 4, 0, 0, 0, 0);
        gameField.mappingGameTile(x, y, null);
        gameField.mappingGameTile(x + 3, y, null);
        gameField.mappingGameTile(x, y + 3, null);
        gameField.mappingGameTile(x + 3, y + 3, null);

    }

    public double getCost() {
        return cost_;
    }
}
