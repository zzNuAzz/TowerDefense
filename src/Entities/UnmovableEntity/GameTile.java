package entities.unmovableEntity;

import entities.GameEntity;
import game.GameField;

public abstract class
GameTile extends GameEntity {
    public void mapping(GameField gameField, int x, int y, int size, int offsetToCenterXL, int offsetToCenterXR, int offsetToCenterYU, int offsetToCenterYD) {
        for (int i = offsetToCenterYU; i < size - offsetToCenterYD; i++) {
            for (int j = offsetToCenterXL; j < size - offsetToCenterXR; j++) {
                gameField.mappingGameTile(x + j, y + i, this);
            }
        }
    }
}
