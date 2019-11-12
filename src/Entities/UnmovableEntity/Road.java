package entities.unmovableEntity;

import game.GameField;

public class Road extends GameTile {

    public Road(GameField gameField, int x, int y, int type) {
        switch (type) {
            case 0:
                mapping(gameField, x, y, 4, 0, 0, 0, 0);
                gameField.mappingGameTile(x + 3, y + 3, null);
                break;
            case 1:
                mapping(gameField, x, y, 4, 0, 0, 0, 1);
                break;
            case 2:
                mapping(gameField, x, y, 4, 0, 0, 0, 0);
                gameField.mappingGameTile(x , y + 3, null);
                break;
            case 3:
                mapping(gameField,x,y,4,1,0,1,0);
                gameField.mappingGameTile(x+1,y+1,null);
                break;
            case 4:
                mapping(gameField,x,y,4,0,1,1,0);
                gameField.mappingGameTile(x+2,y+1,null);
                break;
            case 5:
                mapping(gameField, x, y, 4, 0, 1, 0, 0);
                break;
            case 7:
                mapping(gameField, x, y, 4, 1, 0, 0, 0);
                break;
            case 8:

                mapping(gameField,x,y,4,1,0,0,1);
                gameField.mappingGameTile(x+1,y+2,null);
                break;
            case 9:

                mapping(gameField,x,y,4,0,1,0,1);
                gameField.mappingGameTile(x+2,y+2,null);
                break;
            case 10:
                mapping(gameField, x, y, 4, 0, 0, 0, 0);
                gameField.mappingGameTile(x + 3, y, null);
                break;
            case 11:
                mapping(gameField, x, y, 4, 0, 0, 1, 0);
                break;
            case 12:
                mapping(gameField, x, y, 4, 0, 0, 0, 0);
                gameField.mappingGameTile(x, y, null);
                break;
        }
    }

    @Override
    public void update(long t) {

    }

    @Override
    public void draw() {

    }
}
