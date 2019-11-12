package entities.unmovableEntity.obstacles;

import game.GameField;
import graphics.Sprite;

public class BigBush extends Obstacle {
    public BigBush(GameField gameField, int x, int y) {
        super(x, y);


        mapping(gameField, x, y, 4, 0,0,0,0);
    }

    @Override
    public void draw() {
        Sprite.bigBushObstacle.draw(xPos_, yPos_);
    }
}