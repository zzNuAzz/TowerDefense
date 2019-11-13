package game;

import entities.unmovableEntity.towers.MachineGunTower;
import entities.unmovableEntity.towers.NormalTower;
import entities.unmovableEntity.towers.SniperTower;
import entities.unmovableEntity.towers.Tower;
import graphics.Coordinate;
import graphics.GCSingleton;
import graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class GameController extends AnimationTimer {
    private static GameController controllerInstance = null;
    public GameField gameField;
    private boolean isRunning = false;

    public static Button startButton;
    public static Button stopButton;
    public static Button nextWaveButton;
    public static Button normalTowerButton;
    public static Button sniperTowerButton;
    public static Button machineGunTowerButton;

    public Tower towerOnDrag = null;
    private long tick = 0;

    static {
        // startButton
        startButton = new Button("Start");
        startButton.setMinSize(100, 100);
        startButton.setMaxSize(100, 100);
        startButton.setOnAction(e -> {
            controllerInstance.isRunning = true;
        });

        //stopButton
        stopButton = new Button("Stop");
        stopButton.setMinSize(100, 100);
        stopButton.setMaxSize(100, 100);
        stopButton.setOnAction(e -> {
            controllerInstance.isRunning = false;
        });

        //nextWave Button
        nextWaveButton = new Button("Next Wave");
        nextWaveButton.setMinSize(200, 75);
        nextWaveButton.setOnAction(e -> {
            controllerInstance.gameField.nextWave();
        });
        //normalTowerButton
        normalTowerButton = new Button("Normal Tower\n" + Config.NORMAL_TOWER_COST + "$", new ImageView(Sprite.normalTowerTop.scale(.6, .6).getImage()));
        normalTowerButton.setMaxWidth(200);
        normalTowerButton.setOnAction(e -> {
            controllerInstance.towerOnDrag = new NormalTower(controllerInstance.gameField);
        });

        //sniperTowerButton
        sniperTowerButton = new Button("Sniper Tower\n" + Config.SNIPER_TOWER_COST + "$", new ImageView(Sprite.sniperTowerTop.scale(.6, .6).getImage()));
        sniperTowerButton.setMaxWidth(200);
        sniperTowerButton.setOnAction(e -> {
            controllerInstance.towerOnDrag = new SniperTower(controllerInstance.gameField);
        });

        //machineGunButton
        machineGunTowerButton = new Button("Machine Tower\n" + Config.MACHINE_GUN_TOWER_COST + "$", new ImageView(Sprite.machineGunTowerTop.scale(.6, .6).getImage()));
        machineGunTowerButton.setMaxWidth(200);
        machineGunTowerButton.setOnAction(e -> {
            controllerInstance.towerOnDrag = new MachineGunTower(controllerInstance.gameField);
        });
    }

    public static void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            if (controllerInstance.towerOnDrag != null) {
                controllerInstance.gameField.createTower(controllerInstance.towerOnDrag);
                controllerInstance.towerOnDrag = null;
            }
        } else if (mouseEvent.getButton() == MouseButton.SECONDARY) controllerInstance.towerOnDrag = null;
    }

    public static void mouseMoved(MouseEvent mouseEvent) {
        if (controllerInstance.towerOnDrag != null) {
            double x = Coordinate.fixAccuracy(mouseEvent.getX() - Config.TILE_SIZE / 2.);
            double y = Coordinate.fixAccuracy(mouseEvent.getY() - Config.TILE_SIZE / 2.);
            controllerInstance.towerOnDrag.setPosition(x - x % (Config.TILE_SIZE / 4.), y - y % (Config.TILE_SIZE / 4.));

        }
    }

    private void tick() {
        tick++;
    }

    private GameController() {
        gameField = GameStage.loadGameField("Level/level1.txt");
    }

    public static GameController getInstance() {
        if (controllerInstance == null) controllerInstance = new GameController();
        return controllerInstance;
    }

    @Override
    public void handle(long now) {
        //update

        if (isRunning) {
            gameField.update((tick));
            tick();
        }

        //draw
        gameField.draw();

        if (!isRunning) {
            GCSingleton.getInstance().setFill(new Color(1, 1, 1, 0.5d));
            Coordinate.drawGrid(0, 0, Config.TILE_SIZE * 20, Config.TILE_SIZE * 12, Config.TILE_SIZE / 4);
        }

        if (towerOnDrag != null) {
            towerOnDrag.draw();
            if (towerOnDrag.canPlace(gameField.getCoins())) GCSingleton.getInstance().setFill(new Color(0, 1, 0, 0.5));
            else GCSingleton.getInstance().setFill(new Color(1, 0, 0, 0.5));
            Coordinate.drawGrid(0, 0, Config.TILE_SIZE * 20, Config.TILE_SIZE * 12, Config.TILE_SIZE / 4);
        }

    }
}
