package Game;

import Entities.IRender;
import Entities.MovableEntity.Enemy;
import Entities.MovableEntity.NormalEnemy;
import Graphics.BackGround;
import Graphics.Coordinate;
import com.sun.javafx.geom.Vec2f;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Collections;

/*
             |================================================================
             |Class này quản lí các đối tượng có trong 1 màn chơi
             |
             |================================================================
*/
public class GameField implements IRender {
    private BackGround backGround;

    private ArrayList<Enemy> enemyList = new ArrayList<>();

    public GameField(String path, int themeIndex) {
        backGround = new BackGround(path, themeIndex);
    }

    public int getTiles(int i, int j) {
        return backGround.getTiles(i, j);
    }

    @Override
    public void update(long t) {
        System.out.println("t = " + t);

        if(t / 1000000000 > enemyList.size()) {
            NormalEnemy e = new NormalEnemy(this);
            e.setPosition(Coordinate.getPixels(-1,1));
            e.setDirect(Enemy.Direct.RIGHT);
            enemyList.add(e);
        }
        for(Enemy e : enemyList) {
            e.update(t);
        }
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        backGround.draw(graphicsContext);
        for(Enemy e : enemyList) {
            e.draw(graphicsContext);
        }
    }
}
