package entities.unmovableEntity.obstacles;

import game.Config;
import game.GameField;
import graphics.Sprite;

public class Stone extends Obstacle {
    public Stone(GameField gameField, int x, int y) {
        super(x, y, Config.STONE_COST);
        sizeX_ = Sprite.stone.getWidth();
        sizeY_ = Sprite.stone.getHeight();
        for (int i = y; i < y + 3; i++) {
            for (int j = x; j < x + 5; j++)
                gameField.mappingGameTile(j, i, this);
        }
    }

    @Override
    protected void unMapping() {
        int x = (int) (xPos_ / 16);
        int y = (int) (yPos_ / 16);
        for (int i = y; i < y + 3; i++) {
            for (int j = x; j < x + 5; j++)
                gameField_.mappingGameTile(j, i, null);
        }
    }

    @Override
    public void draw() {
        Sprite.stone.draw(xPos_, yPos_);
    }
}
