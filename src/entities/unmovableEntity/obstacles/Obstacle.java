package entities.unmovableEntity.obstacles;

import entities.Saleable;
import entities.unmovableEntity.GameTile;
import game.Config;
import game.GameController;
import game.GameField;
import sound.Sound;

public abstract class Obstacle extends GameTile implements Saleable {
    private int cost_;
    protected GameField gameField_;

    public Obstacle(int x, int y, int cost) {
        xPos_ = x * 16;
        yPos_ = y * 16;
        this.cost_ = cost;

    }

    @Override
    public void update(long t) {

    }

    protected abstract void unMapping();

    @Override
    public void sell() {
        if (GameController.getInstance().gameField.getCoins() < getSell()) return;
        GameController.getInstance().gameField.deleteTile(this);
        GameController.getInstance().gameField.addCoin(-getSell());
        unMapping();

    }

    @Override
    public int getSell() {
        return cost_;
    }
}
