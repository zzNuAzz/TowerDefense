package game;

import com.sun.javafx.geom.Vec2d;
import entities.unmovableEntity.obstacles.Bush;
import entities.unmovableEntity.obstacles.Road;
import entities.unmovableEntity.obstacles.Stone;
import entities.unmovableEntity.obstacles.Tree;
import myUtils.Pair;
import entities.movableEntity.enemies.*;
import entities.unmovableEntity.GameTile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GameStage {

    public static GameField loadGameField(String path) {
        Scanner sc;
        try {
            sc = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            System.err.println(e.toString());
            return null;
        }

        ArrayList<GameTile> tileArrayList = new ArrayList<>();
        ArrayList<Queue<Pair<Enemy, Integer>>> enemySpawner = new ArrayList<>();
        int numRoutes = sc.nextInt();
        int numPaths = sc.nextInt();
        sc.nextLine();
        ArrayList<Queue<Vec2d>> paths = new ArrayList<>();
        for (int i = 0; i < numPaths; i++) {
            paths.add(new LinkedList<>());
            String line = sc.nextLine();
            String[] ps = line.split("->");
            for (String p : ps) {
                String[] point = p.trim().split("\\s+");
                paths.get(i).add(new Vec2d(Integer.parseInt(point[0]), Integer.parseInt(point[1])));
            }
        }
        int coins = sc.nextInt();
        int row = sc.nextInt();
        int col = sc.nextInt();
        GameField gameField = new GameField(tileArrayList, enemySpawner, coins, row, col);
        gameField.setEnemySpawner(enemySpawner);


        sc.nextLine();
        for (int i = 0; i < row; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '1') {
                    gameField.mappingGameTile(j, i, new Road());
                }
            }
        }
        int wave = 0;
        Random rand = new Random();
        while (sc.hasNext()) {
            switch (sc.next()) {
                case "wave":
                    wave = sc.nextInt();
                    while (wave > enemySpawner.size() - 1) enemySpawner.add(new LinkedList<>());
                    break;
                case "NormalOrk": {
                    int number = sc.nextInt();
                    int way = sc.nextInt();
                    int tickDelay = sc.nextInt();
                    while (number-- > 0) {
                        Enemy normalEnemy = new NormalOrk(gameField, new LinkedList<>(paths.get(way == -1 ? rand.nextInt(numPaths-numRoutes) : way)));
                        enemySpawner.get(wave).add(new Pair<>(normalEnemy, tickDelay));

                    }
                    break;
                }
                case "BatEnemy": {
                    int number = sc.nextInt();
                    int way = sc.nextInt();
                    int tickDelay = sc.nextInt();
                    while (number-- > 0) {
                        Enemy smallerEnemy = new BatEnemy(gameField, new LinkedList<>(paths.get(way == -1 ? rand.nextInt(numPaths-numRoutes) : way)));
                        enemySpawner.get(wave).add(new Pair<>(smallerEnemy, tickDelay));
                    }
                    break;
                }
                case "TankOrk": {
                    int number = sc.nextInt();
                    int way = sc.nextInt();
                    int tickDelay = sc.nextInt();
                    while (number-- > 0) {
                        Enemy tankerEnemy = new TankOrk(gameField, new LinkedList<>(paths.get(way == -1 ? rand.nextInt(numPaths-numRoutes) : way)));
                        enemySpawner.get(wave).add(new Pair<>(tankerEnemy, tickDelay));
                    }
                    break;
                }
                case "Troll": {
                    int number = sc.nextInt();
                    int way = sc.nextInt();
                    int tickDelay = sc.nextInt();
                    while (number-- > 0) {
                        Enemy bossEnemy = new TrollEnemy(gameField, new LinkedList<>(paths.get(way == -1 ? rand.nextInt(numPaths-numRoutes) : way)));
                        enemySpawner.get(wave).add(new Pair<>(bossEnemy, tickDelay));
                    }
                    break;
                }
                case "Bush": {
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    tileArrayList.add(new Bush(gameField, x, y));
                    break;
                }
                case "Tree": {
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    tileArrayList.add(new Tree(gameField, x, y));
                    break;
                }
                case "Stone": {
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    tileArrayList.add(new Stone(gameField, x, y));
                    break;
                }
            }
        }
        sc.close();
        return gameField;
    }

}
