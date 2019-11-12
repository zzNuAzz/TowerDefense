package entities.unmovableEntity.Obstacle;

import game.GameField;
import graphics.Sprite;

public class BigRock extends Obstacle {
    public BigRock(GameField gameField, int x, int y) {
        super(x, y);


        mapping(gameField, x, y, 4,0,0,0,0);
    }

    @Override
    public void draw() {
        Sprite.bigRockObstacle.draw(xPos_, yPos_);
    }
}
