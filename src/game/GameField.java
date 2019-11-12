package game;

import myUtils.Pair;
import entities.IRender;
import entities.movableEntity.bullet.Bullet;
import entities.movableEntity.enemies.*;
import entities.unmovableEntity.GameTile;
import entities.unmovableEntity.towers.Tower;
import graphics.Coordinate;
import graphics.GCSingleton;
import graphics.Sprite;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.*;

/*
             |================================================================
             |Class này quản lí các đối tượng có trong 1 màn chơi
             |================================================================
*/
public class GameField implements IRender {
    private ArrayList<ArrayList<Integer>> map_;
    private ArrayList<Enemy> enemyList_ = new ArrayList<>();
    private ArrayList<GameTile> tileList_ = new ArrayList<>();
    private ArrayList<Bullet> bulletList_ = new ArrayList<>();

    private ArrayList<Queue<Pair<Enemy, Integer>>> enemySpawner = new ArrayList<>();

    private Queue<Enemy> deleteEnemyQueue = new LinkedList<>();
    private Queue<Bullet> deleteBulletQueue = new LinkedList<>();

    private HashMap<Integer, GameTile> hashMap = new HashMap<>(); // mapping gametile

    private int wave_ = 0;
    private int coins = 200;
    private int healthPoint = 20;

    public GameField(ArrayList<ArrayList<Integer>> map) {
        this.map_ = map;
    }

    public GameField(ArrayList<ArrayList<Integer>> map_, ArrayList<GameTile> tileList_, ArrayList<Queue<Pair<Enemy, Integer>>> enemySpawner, int coins) {
        this.map_ = map_;
        this.tileList_ = tileList_;
        this.enemySpawner = enemySpawner;
        this.coins = coins;
    }

    public int getTiles(int i, int j) {
        if (i < 0 || i >= Config.ROW_NUMBER) return Integer.MAX_VALUE;
        if (j < 0 || j >= Config.COL_NUMBER) return Integer.MAX_VALUE;
        return map_.get(i).get(j);
    }

    public ArrayList<Enemy> getEnemyList() {
        return enemyList_;
    }


    public void deleteEntity(Enemy enemy) {
        deleteEnemyQueue.add(enemy);
    }

    public void damage(double damage) {
        healthPoint -= damage;
    }


    public void setEnemySpawner(ArrayList<Queue<Pair<Enemy, Integer>>> enemySpawner) {
        this.enemySpawner = enemySpawner;
    }

    private void spawnEnemy(long t) {
        if (!enemySpawner.get(wave_).isEmpty()) {
            Pair<Enemy, Integer> enemy = enemySpawner.get(wave_).peek();
            if (enemy == null) {
                enemySpawner.get(wave_).poll();
                return;
            }
            if (enemy.getValue() > 0) enemy.setValue(enemy.getValue() - 1);
            else {
                enemyList_.add(enemy.getKey());
                enemySpawner.get(wave_).poll();
            }
        }
    }

    public void addBullet(Bullet bullet) {
        bulletList_.add(bullet);
    }


    public void deleteBullet(Bullet bullet) {
        deleteBulletQueue.add(bullet);
    }

    @Override
    public void update(long t) {
        spawnEnemy(t);
        //enemy
        for (Enemy enemy : enemyList_) enemy.update(t);
        //tower + obstacle
        for (GameTile tile : tileList_) tile.update(t);
        //bullet
        for (Bullet bullet : bulletList_) bullet.update(t);

        while (!deleteEnemyQueue.isEmpty()) enemyList_.remove(deleteEnemyQueue.poll());
        while (!deleteBulletQueue.isEmpty()) bulletList_.remove(deleteBulletQueue.poll());
    }

    @Override
    public void draw() {
        //background
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 20; j++) {
                Sprite.listTheme.get(0).get(map_.get(i).get(j)).draw(j * Config.TILE_SIZE, i * Config.TILE_SIZE);
            }
        }
        //tower + obstacle
        for (GameTile tile : tileList_) tile.draw();
        //enemy
        for (Enemy enemy : enemyList_) enemy.draw();
        for (Enemy enemy : enemyList_) enemy.drawHpBar();
        //bullet
        for (Bullet bullet : bulletList_) bullet.draw();


        //gameInfo
        GCSingleton.getInstance().setFill(Color.BLUE);
        GCSingleton.getInstance().setFont(new Font(36));
        GCSingleton.getInstance().fillText("Wave #" + (wave_ + 1) + "/" + enemySpawner.size() + "\tHP: " + healthPoint + "\tCoins: " + coins, 0, 36);

    }


    public void mappingGameTile(int x, int y, GameTile value) {
        hashMap.put(y * Config.COL_NUMBER * 4 + x, value);
    }

    public GameTile getMappingGameTile(int x, int y) {
        if (hashMap.containsKey(y * Config.COL_NUMBER * 4 + x)) {
            return hashMap.get(y * Config.COL_NUMBER * 4 + x);
        }
        return null;
    }


    public void createTower(Tower towerOnDrag) {

        if (towerOnDrag.canPlace()) {  int x = (int) Coordinate.fixAccuracy(towerOnDrag.getXPos() / (Config.TILE_SIZE / 4.));
            int y = (int) Coordinate.fixAccuracy(towerOnDrag.getYPos() / (Config.TILE_SIZE / 4.));
            tileList_.add(towerOnDrag);
            towerOnDrag.mapping(this, x,y);
            towerOnDrag.setPosition(x * Config.TILE_SIZE / 4., y * Config.TILE_SIZE / 4.);
        }
    }


    public void nextWave() {
        if (enemyList_.isEmpty() && wave_ < enemySpawner.size() - 1 && enemySpawner.get(wave_).isEmpty()) wave_++;

    }
}
