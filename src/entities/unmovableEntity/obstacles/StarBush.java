package entities.unmovableEntity.obstacles;

import game.GameField;
import graphics.Sprite;

public class StarBush extends Obstacle {
    public StarBush(GameField gameField, int x, int y) {
        super(x, y);
        mapping(gameField, x, y, 4, 0, 0, 0, 0);
        gameField.mappingGameTile(x, y, null);
        gameField.mappingGameTile(x + 3, y, null);
        gameField.mappingGameTile(x, y + 3, null);
        gameField.mappingGameTile(x + 3, y + 3, null);
    }

    @Override
    public void draw() {
        Sprite.starBushObstacle.draw(xPos_, yPos_);
    }
}
