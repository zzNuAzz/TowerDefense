package Entities.MovableEntity;

import Game.GameField;
import Graphics.Coordinate;
import Graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class NormalEnemy extends Enemy {
    public NormalEnemy(GameField gameField) {
        this.gameField = gameField;
        speed = 8;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        Sprite.normalEnemy.draw(graphicsContext, xPos_, yPos_);
        graphicsContext.setFill(Color.RED);
        Coordinate.drawGrid(graphicsContext,(int) xPos_,(int)yPos_,((int) Sprite.normalEnemy.getWidth()), ((int) Sprite.normalEnemy.getHeight()), 128);
    }

}

