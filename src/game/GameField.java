package game;

import entities.movableEntity.bullet.Bullet;
import myUtils.Pair;
import entities.IRender;
import entities.movableEntity.enemies.*;
import entities.unmovableEntity.GameTile;
import entities.unmovableEntity.towers.Tower;
import myUtils.Coordinate;
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
    private ArrayList<Enemy> enemyList_ = new ArrayList<>();
    private ArrayList<GameTile> tileList_ = new ArrayList<>();
    private ArrayList<Bullet> bulletList_ = new ArrayList<>();

    private ArrayList<Queue<Pair<Enemy, Integer>>> enemySpawner = new ArrayList<>();

    private Queue<Enemy> deleteEnemyQueue = new LinkedList<>();
    private Queue<Bullet> deleteBulletQueue = new LinkedList<>();
    private Queue<GameTile> deleteTileQueue = new LinkedList<>();

    private final int COL_NUMBER;
    private final int ROW_NUMBER;

    private HashMap<Integer, GameTile> hashMap = new HashMap<>(); // mapping GameTile

    private int wave_ = 0;
    private int coins = 200;
    private int healthPoint = 10;


    public void addCoin(int value) {
        coins += value;
    }

    public enum GameStatus {VICTORY, DEFEAT, RUNNING}

    public GameStatus stt = GameStatus.RUNNING;

    public GameStatus getStt() {
        return stt;
    }

    public GameField(ArrayList<GameTile> tileList_, ArrayList<Queue<Pair<Enemy, Integer>>> enemySpawner, int coins, int row, int col) {
        this.tileList_ = tileList_;
        this.enemySpawner = enemySpawner;
        this.coins = coins;
        this.ROW_NUMBER = row;
        this.COL_NUMBER = col;
    }


    public ArrayList<Enemy> getEnemyList() {
        return enemyList_;
    }


    public void deleteEntity(Enemy enemy) {
        deleteEnemyQueue.add(enemy);
        if (enemy.getHealthPoint() <= 0) coins += enemy.getRewardCoins();
    }

    public void deleteTile(GameTile gameTile) {
        deleteTileQueue.add(gameTile);
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
                enemy.getKey().setCreatedTick(t);
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
        if (stt == GameStatus.RUNNING && GameController.getInstance().isRunning) {
            spawnEnemy(t);
            //enemy
            for (Enemy enemy : enemyList_) enemy.update(t);
            //tower + obstacle
            for (GameTile tile : tileList_) tile.update(t);
            //bullet
            for (Bullet bullet : bulletList_) bullet.update(t);
        }

        while (!deleteEnemyQueue.isEmpty()) enemyList_.remove(deleteEnemyQueue.poll());
        while (!deleteBulletQueue.isEmpty()) bulletList_.remove(deleteBulletQueue.poll());
        while (!deleteTileQueue.isEmpty()) {
            tileList_.remove(deleteTileQueue.poll());
        }

        if (healthPoint <= 0) stt = GameStatus.DEFEAT;
        if (enemyList_.isEmpty() && enemySpawner.get(enemySpawner.size() - 1).isEmpty()) stt = GameStatus.VICTORY;
    }

    @Override
    public void draw() {
        //background
//        for (int i = 0; i < 12; i++) {
//            for (int j = 0; j < 20; j++) {
//                Sprite.listTheme.get(0).get(map_.get(i).get(j)).draw(j * Config.TILE_SIZE, i * Config.TILE_SIZE);
//            }
//        }
        Sprite.background.draw(0, 0);
        //enemy
        for (Enemy enemy : enemyList_) enemy.draw();
        for (Enemy enemy : enemyList_) enemy.drawHpBar();
        //tower + obstacle
        for (GameTile tile : tileList_) tile.draw();
        //bullet
        for (Bullet bullet : bulletList_) bullet.draw();


        //gameInfo
        Sprite.infoBar.draw(600, 0);
        Sprite.getGC().setFill(Color.BLUE);
        Sprite.getGC().setFont(new Font(34));
        Sprite.getGC().fillText("Wave #" + (wave_ + 1) + "/" + enemySpawner.size(), 618, 45);
        Sprite.getGC().fillText(String.valueOf(coins), 820, 45);
        Sprite.getGC().fillText(String.valueOf(healthPoint), 960, 45);

        if (stt == GameStatus.VICTORY) {
            Sprite.victory.draw(300, 100);
        } else if (stt == GameStatus.DEFEAT) {
            Sprite.defeat.draw(300, 100);
        }
    }


    public int getCoins() {
        return coins;
    }

    public void mappingGameTile(int x, int y, GameTile value) {
        hashMap.put(y * COL_NUMBER + x, value);
    }

    public GameTile getMappingGameTile(int x, int y) {
        if (hashMap.containsKey(y * COL_NUMBER + x)) {
            return hashMap.get(y * COL_NUMBER + x);
        }
        return null;
    }


    public void createTower(Tower towerOnDrag) {
        if (towerOnDrag.canPlace(coins)) {
            int x = (int) Coordinate.fixAccuracy(towerOnDrag.getXPos() / (Config.TILE_SIZE / 4.));
            int y = (int) Coordinate.fixAccuracy(towerOnDrag.getYPos() / (Config.TILE_SIZE / 4.));
            tileList_.add(towerOnDrag);
            coins -= towerOnDrag.getCostLV(0);
            towerOnDrag.mapping(this, x, y);
            towerOnDrag.setPosition(x * Config.TILE_SIZE / 4., y * Config.TILE_SIZE / 4.);
        }
    }


    public void nextWave() {
        if (enemyList_.isEmpty() && wave_ < enemySpawner.size() - 1 && enemySpawner.get(wave_).isEmpty()) wave_++;
    }
}
