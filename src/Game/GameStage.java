package game;

import myUtils.Pair;
import entities.movableEntity.enemies.*;
import entities.unmovableEntity.GameTile;
import entities.unmovableEntity.obstacles.*;
import entities.unmovableEntity.Road;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GameStage {

    public static GameField loadGameField(String path) {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            System.err.println(e.toString());
            return null;
        }
        ArrayList<ArrayList<Integer>> map = new ArrayList<>();
        for (int i = 0; i < Config.ROW_NUMBER; i++) {
            map.add(new ArrayList<Integer>());
            for (int j = 0; j < Config.COL_NUMBER; j++) map.get(i).add(sc.nextInt());
        }
        ArrayList<GameTile> tileArrayList = new ArrayList<>();
        ArrayList<Queue<Pair<Enemy, Integer>>> enemySpawner = new ArrayList<>();
        GameField gameField = new GameField(map, tileArrayList, enemySpawner, sc.nextInt());

        for (int i = 0; i < Config.ROW_NUMBER; i++) {
            for (int j = 0; j < Config.COL_NUMBER; j++)
                new Road(gameField, j * 4, i * 4, map.get(i).get(j));
        }

        gameField.setEnemySpawner(enemySpawner);
        int wave = 0;
        while (sc.hasNext()) {
            switch (sc.next()) {
                case "wave":
                    wave = sc.nextInt();
                    while (wave > enemySpawner.size() - 1) enemySpawner.add(new LinkedList<>());
                    break;
                case "NormalEnemy": {
                    int number = sc.nextInt();
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    int tickDelay = sc.nextInt();
                    while (number-- > 0) {
                        NormalEnemy normalEnemy = new NormalEnemy(gameField);
                        enemySpawner.get(wave).add(new Pair<>(normalEnemy, tickDelay));
                        normalEnemy.setPosition(x * Config.TILE_SIZE, y * Config.TILE_SIZE);
                        normalEnemy.setDirect(Enemy.Direct.RIGHT);
                    }
                    break;
                }
                case "SmallerEnemy": {
                    int number = sc.nextInt();
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    int tickDelay = sc.nextInt();
                    while (number-- > 0) {
                        SmallerEnemy smallerEnemy = new SmallerEnemy(gameField);
                        enemySpawner.get(wave).add(new Pair<>(smallerEnemy, tickDelay));
                        smallerEnemy.setPosition(x * Config.TILE_SIZE, y * Config.TILE_SIZE);
                        smallerEnemy.setDirect(Enemy.Direct.RIGHT);
                    }
                    break;
                }
                case "TankerEnemy": {
                    int number = sc.nextInt();
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    int tickDelay = sc.nextInt();
                    while (number-- > 0) {
                        TankerEnemy tankerEnemy = new TankerEnemy(gameField);
                        enemySpawner.get(wave).add(new Pair<>(tankerEnemy, tickDelay));
                        tankerEnemy.setPosition(x * Config.TILE_SIZE, y * Config.TILE_SIZE);
                        tankerEnemy.setDirect(Enemy.Direct.RIGHT);
                    }
                    break;
                }
                case "BossEnemy": {
                    int number = sc.nextInt();
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    int tickDelay = sc.nextInt();
                    while (number-- > 0) {
                        BossEnemy bossEnemy = new BossEnemy(gameField);
                        enemySpawner.get(wave).add(new Pair<>(bossEnemy, tickDelay));
                        bossEnemy.setPosition(x * Config.TILE_SIZE, y * Config.TILE_SIZE);
                        bossEnemy.setDirect(Enemy.Direct.RIGHT);
                    }
                    break;
                }
                case "SmallBush": {
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    tileArrayList.add(new SmallBush(gameField, x, y));
                    break;
                }
                case "BigBush": {
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    tileArrayList.add(new BigBush(gameField, x, y));
                    break;
                }
                case "StarBush": {
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    tileArrayList.add(new StarBush(gameField, x, y));
                    break;
                }
                case "SmallRock": {
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    tileArrayList.add(new SmallRock(gameField, x, y));
                    break;
                }
                case "BigRock": {
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    tileArrayList.add(new BigRock(gameField, x, y));
                    break;
                }
            }
        }
        sc.close();
        return gameField;
    }

}
