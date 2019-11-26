package entities.unmovableEntity.obstacles;

import game.Config;
import game.GameField;
import graphics.Sprite;

public class Tree extends Obstacle {
    public Tree(GameField gameField, int x, int y) {
        super(x, y, Config.TREE_COST);
        this.gameField_ = gameField;
        this.sizeX_ = Sprite.tree.getWidth();
        this.sizeY_ = Sprite.tree.getHeight();
        for (int i = y; i < y + 7; i++)
            for (int j = x; j < x + 6; j++)
                gameField.mappingGameTile(j, i, this);
    }

    @Override
    protected void unMapping() {
        int x = (int) (xPos_ / 16);
        int y = (int) (yPos_ / 16);
        for (int i = y; i < y + 7; i++)
            for (int j = x; j < x + 6; j++)
                gameField_.mappingGameTile(j, i, null);
    }

    @Override
    public void draw() {
        Sprite.tree.draw(xPos_, yPos_);
    }
}
