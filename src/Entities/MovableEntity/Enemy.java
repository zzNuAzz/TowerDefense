package Entities.MovableEntity;

import Game.GameField;
import Graphics.Coordinate;

public abstract class Enemy extends MovableEntity  {
    protected double healthPoint;
    protected double speed;
    protected double ammor;
    protected double rewardCoin;
    protected GameField gameField;
    protected Direct direct = Direct.RIGHT;
    public enum Direct{LEFT, RIGHT, UP, DOWN}
    public double getHealthPoint() { return healthPoint; }
    public void setHealthPoint(double healthPoint) { this.healthPoint = healthPoint; }

    public double getSpeed() { return speed; }
    public void setSpeed(double speed) { this.speed = speed; }

    public double getAmmor() { return ammor; }
    public void setAmmor(double ammor) { this.ammor = ammor; }

    public double getRewardCoin() { return rewardCoin; }
    public void setRewardCoin(double rewardCoin) { this.rewardCoin = rewardCoin; }

    public GameField getGameField() { return gameField; }
    public void setGameField(GameField gameField) { this.gameField = gameField; }

    public Direct getDirect() { return direct; }
    public void setDirect(Direct direct) { this.direct = direct; }


    public Direct findDirect() {
        int j = Coordinate.getTiles((int)(xPos_));
        int i = Coordinate.getTiles((int)(yPos_));
        if(direct == Direct.RIGHT) {
            if(gameField.getTiles(i,j +1) == 4 && xPos_ % Coordinate.TILE_SIZE == 0) return Direct.DOWN;
            if(gameField.getTiles(i+1,j +1) == 9 && xPos_ % Coordinate.TILE_SIZE == 0) return Direct.UP;
            return Direct.RIGHT;
        }
        if (direct == Direct.DOWN) {
            if(gameField.getTiles( i +1,j) == 8 && yPos_ % Coordinate.TILE_SIZE == 0) return Direct.RIGHT;
            if(gameField.getTiles(i +1,j +1) == 9 && yPos_ % Coordinate.TILE_SIZE == 0) return Direct.LEFT;
            return Direct.DOWN;

        }
//        j = Coordinate.getTiles((int) (xPos_));
//        i = Coordinate.getTiles((int) (yPos_));
        if (direct == Direct.LEFT) {
            if (gameField.getTiles(i+1, j) == 8 && xPos_ % Coordinate.TILE_SIZE == 0) return Direct.UP;
            if (gameField.getTiles(i, j) == 3 && xPos_ % Coordinate.TILE_SIZE == 0) return Direct.DOWN;
            return Direct.LEFT;
        }
        //direct == Direct.UP
        if (gameField.getTiles(i , j+1) == 4 && yPos_ % Coordinate.TILE_SIZE == 0) return Direct.LEFT;
        if (gameField.getTiles(i, j) == 3 && yPos_ % Coordinate.TILE_SIZE == 0) return Direct.RIGHT;

        return Direct.UP;

    }
    @Override
    public void update(long t) {
        super.update(t);
        direct = findDirect();
        switch (direct) {
            case RIGHT: setVelocity(speed,0);
                break;
            case LEFT: setVelocity(-speed, 0);
                break;
            case UP: setVelocity(0,-speed);
                break;
            case DOWN: setVelocity(0, speed);
                break;
        }
    }
}
