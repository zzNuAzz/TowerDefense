package entities.unmovableEntity.obstacles;

import game.GameField;
import graphics.Sprite;

public class SmallRock extends Obstacle {
    public SmallRock(GameField gameField, int x, int y) {
        super(x, y);

        mapping(gameField, x, y, 4,1,1,1,1);
    }

    @Override
    public void draw() {
        Sprite.smallRockObstacle.draw(xPos_, yPos_);
    }
}