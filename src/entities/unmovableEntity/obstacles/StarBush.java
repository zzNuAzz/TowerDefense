package entities.unmovableEntity.obstacles;

import game.GameField;
import graphics.Sprite;

public class StarBush extends Obstacle {
    public StarBush(GameField gameField, int x, int y) {
        super(x, y);
        mapping(gameField, x, y, 4,0,0,0,0);
    }

    @Override
    public void draw() {
        Sprite.starBushObstacle.draw(xPos_, yPos_);
    }
}
