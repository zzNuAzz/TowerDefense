package entities.unmovableEntity.obstacles;

import entities.unmovableEntity.GameTile;
import game.Config;

public abstract class Obstacle extends GameTile {
    protected int size;

    public Obstacle(int x, int y) {
        xPos_ = x * (Config.TILE_SIZE / 4d);
        yPos_ = y * (Config.TILE_SIZE / 4d);
    }

    @Override
    public void update(long t) {

    }


}
