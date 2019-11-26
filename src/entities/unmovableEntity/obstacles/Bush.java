package entities.unmovableEntity.obstacles;

import game.Config;
import game.GameField;
import graphics.Sprite;

public class Bush extends Obstacle {
    public Bush(GameField gameField, int x, int y) {
        super(x, y, Config.BUSH_COST);
        this.gameField_ = gameField;
        this.sizeX_ = Sprite.bush.getWidth();
        this.sizeY_ = Sprite.bush.getHeight();
        for (int i = y; i < y + 2; i++)
            for (int j = x; j < x + 4; j++)
                gameField.mappingGameTile(j, i, this);
    }

    @Override
    protected void unMapping() {
        int x = (int) (xPos_ / 16);
        int y = (int) (yPos_ / 16);
        for (int i = y; i < y + 4; i++)
            for (int j = x; j < x + 4; j++)
                gameField_.mappingGameTile(j, i, null);
    }

    @Override
    public void draw() {
        Sprite.bush.draw(xPos_, yPos_);
    }
}
