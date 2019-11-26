package game;

import entities.unmovableEntity.GameTile;
import entities.unmovableEntity.obstacles.Road;
import entities.unmovableEntity.towers.*;
import gui.ClickCircle;
import gui.MainMenu;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import main.Main;
import myUtils.Coordinate;
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
    public boolean isRunning = false;


    public static Button startButton;
    public static Button stopButton;
    public static Button nextWaveButton;
    public static Button exitButton;
    public static Button ironTowerButton;
    public static Button fireTowerButton;
    public static Button archerTowerButton;


//    public static Button exp;
//    public static Button imp;

    private ClickCircle clickCircle = new ClickCircle();

    private Tower towerOnDrag = null;
    private long tick = 0;

    static {

//        exp = new Button("export");
//        exp.setOnAction(e -> {
//            try {
//                //Bước 1: Tạo đối tượng luồng và liên kết nguồn dữ liệu
//                File f = new File("mydata3.txt");
//                FileWriter fw = new FileWriter(f);
//                //Bước 2: Ghi dữ liệu
//                for (int i = 0; i < 14 * 4; i++) {
//                    for (int j = 0; j < 17 * 4; j++) {
//                        if(GameController.m[i][j]) {
//                            fw.write(1 + " ");
//                        }
//                        else fw.write(0 + " ");
//                    }
//                    fw.write("\n");
//                }
//                //Bước 3: Đóng luồng
//                fw.close();
//            } catch (IOException ex) {
//                System.out.println("Loi ghi file: " + ex);
//            }
//        });
//        imp = new Button("import");
//        imp.setOnAction(e -> {
//            try {
//                //Bước 1: Tạo đối tượng luồng và liên kết nguồn dữ liệu
//                File src = new File("mydata3.txt");
//                Scanner sc = new Scanner(src);
//                for (int i = 0; i < 14 * 4; i++) {
//                    for (int j = 0; j < 17 * 4; j++) {
//                        GameController.m[i][j] = sc.nextInt()!=0;
//                    }
//                }
//            } catch (IOException ex) {
//                System.out.println("Loi ghi file: " + ex);
//            }
//        });

        // startButton
        startButton = new Button("Start");
        startButton.setMinSize(100, 100);
        startButton.setMaxSize(100, 100);
        startButton.setOnAction(e -> {
            controllerInstance.isRunning = true;
            controllerInstance.clickCircle.close();
        });

        //stopButton
        stopButton = new Button("Stop");
        stopButton.setMinSize(100, 100);
        stopButton.setMaxSize(100, 100);
        stopButton.setOnAction(e -> {
            controllerInstance.isRunning = false;
            controllerInstance.clickCircle.close();
        });

        exitButton = new Button("Exit");
        exitButton.setMinSize(100, 100);
        exitButton.setOnAction(e -> {
            Main.stage.setScene(MainMenu.scene);
            controllerInstance.stop();
        });
        //nextWave Button
        nextWaveButton = new Button("Next Wave");
        nextWaveButton.setMinSize(100, 100);
        nextWaveButton.setOnAction(e -> {
            controllerInstance.gameField.nextWave();
            controllerInstance.clickCircle.close();
        });

        ironTowerButton = new Button("", new ImageView(new Image("res/icon/1.png")));
        ironTowerButton.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(5), new Insets(5))));
        ironTowerButton.setMaxSize(200, 100);
        ironTowerButton.setOnAction(e -> {
            controllerInstance.towerOnDrag = new IronBallTower(controllerInstance.gameField);
            controllerInstance.clickCircle.close();
        });


        fireTowerButton = new Button("", new ImageView(new Image("res/icon/2.png")));
        fireTowerButton.setBackground(new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, new CornerRadii(5), new Insets(5))));
        fireTowerButton.setMaxSize(200, 100);
        fireTowerButton.setOnAction(e -> {
            controllerInstance.towerOnDrag = new FireBallTower(controllerInstance.gameField);
            controllerInstance.clickCircle.close();
        });

        archerTowerButton = new Button("", new ImageView(new Image("res/icon/3.png")));
        archerTowerButton.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(5), new Insets(5))));
        archerTowerButton.setMaxSize(200, 100);
        archerTowerButton.setOnAction(e -> {
            controllerInstance.towerOnDrag = new ArcherTower(controllerInstance.gameField);
            controllerInstance.clickCircle.close();
        });

    }

    public static void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            if (controllerInstance.towerOnDrag != null) {
                if (controllerInstance.gameField.getStt() == GameField.GameStatus.RUNNING)
                    controllerInstance.gameField.createTower(controllerInstance.towerOnDrag);
                controllerInstance.towerOnDrag = null;
                return;
            }
            int x = (int) (mouseEvent.getX() / (Config.TILE_SIZE / 4));
            int y = (int) (mouseEvent.getY() / (Config.TILE_SIZE / 4));

//            m[y][x] = !m[y][x];
//            System.out.println("m[y][x] = " + m[y][x]);

            GameTile caller = GameController.getInstance().gameField.getMappingGameTile(x, y);
            if (/*caller != null && */getInstance().gameField.getStt() == GameField.GameStatus.RUNNING && !(caller instanceof Road)) {
                controllerInstance.clickCircle.onClick(mouseEvent.getX(), mouseEvent.getY(), caller);
            } else controllerInstance.clickCircle.onClick(mouseEvent.getX(), mouseEvent.getY(), null);

        } else if (mouseEvent.getButton() == MouseButton.SECONDARY) controllerInstance.towerOnDrag = null;
    }


    public static void mouseMoved(MouseEvent mouseEvent) {
//        if(mouseEvent.isControlDown()) {
//            int x = ((int) (mouseEvent.getX() / 16));
//            int y = ((int) (mouseEvent.getY() / 16));
//            GameController.m[y][x] = true;
//        }if(mouseEvent.isAltDown()) {
//            int x = ((int) (mouseEvent.getX() / 16));
//            int y = ((int) (mouseEvent.getY() / 16));
//            GameController.m[y][x] = false;
//        }
        if (controllerInstance.towerOnDrag != null) {
            double x = Coordinate.fixAccuracy(mouseEvent.getX() - Config.TILE_SIZE / 2.);
            double y = Coordinate.fixAccuracy(mouseEvent.getY() - Config.TILE_SIZE / 2.);
            controllerInstance.towerOnDrag.setPosition(x - x % (Config.TILE_SIZE / 4.), y - y % (Config.TILE_SIZE / 4.));
        }
        controllerInstance.clickCircle.onHover(mouseEvent.getX(), mouseEvent.getY());

    }

    private void tick() {
        tick++;
    }

    private GameController() {
    }

    public void newGame(String path) {
        gameField = GameStage.loadGameField(path);
        tick = 0;
    }

    public static GameController getInstance() {
        if (controllerInstance == null) controllerInstance = new GameController();
        return controllerInstance;
    }

//    static boolean[][] m = new boolean[4 * 14][17 * 4];

    @Override
    public void handle(long now) {
        //update

        gameField.update((tick));
        if (gameField.stt == GameField.GameStatus.RUNNING) tick();


        //draw
        gameField.draw();
//        for (int i = 0; i < 14 * 4; i++) {
//            for (int j = 0; j < 17 * 4; j++) {
//                if(m[i][j]) {
//                    Sprite.getGC().setFill(Color.RED);
//                    Sprite.getGC().fillRect(j*16,i*16,16,16);
//                }
//            }
//        }

//        Sprite.getGC().setFill(new Color(1, 0.5, 0.5, 0.5));
//        Sprite.drawGrid(0, 0, Config.TILE_SIZE * 17, Config.TILE_SIZE * 14, Config.TILE_SIZE / 4);

        if (clickCircle != null) {
            clickCircle.update(tick);
            clickCircle.draw();
        }
        if (towerOnDrag != null && gameField.getStt() == GameField.GameStatus.RUNNING) {
            towerOnDrag.draw();
            towerOnDrag.drawRange();
            if (towerOnDrag.canPlace(gameField.getCoins())) Sprite.getGC().setFill(new Color(0, 1, 0, 0.5));
            else Sprite.getGC().setFill(new Color(1, 0, 0, 0.5));
            Sprite.drawGrid(0, 0, Config.TILE_SIZE * 20, Config.TILE_SIZE * 14, Config.TILE_SIZE / 4);
        }

    }


}
