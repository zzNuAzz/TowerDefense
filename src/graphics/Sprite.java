package graphics;

import game.Config;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public class Sprite {
    private static GraphicsContext graphicsContext;

    public static void setGraphicsContext(GraphicsContext graphicsContext) {
        Sprite.graphicsContext = graphicsContext;
    }

    public static GraphicsContext getGC() {
        return Sprite.graphicsContext;
    }

    private Image image_;

    public Sprite(String path) {
        loadImage(path);
    }

    public Sprite(Image image) {
        setImage(image);
    }

    public double getWidth() {
        return image_.getWidth();
    }

    public double getHeight() {
        return image_.getHeight();
    }


    public void setImage(Image image) {
        image_ = image;
    }

    public Image getImage() {
        return image_;
    }

    public void loadImage(String path) {
        setImage(new Image(path));
    }

    public Sprite resizeBound(int x, int y) {
        ImageView imageView = new ImageView(image_);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setViewport(new Rectangle2D(-(x - getWidth()) / 2d, -(y - getHeight()) / 2d, x, y));
        parameters.setFill(Color.TRANSPARENT);
        return new Sprite(imageView.snapshot(parameters, null));
    }

    public Sprite scale(double x, double y) {
        ImageView imageView = new ImageView(image_);
        imageView.setFitWidth(x);
        imageView.setFitHeight(y);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        return new Sprite(imageView.snapshot(parameters, null));
    }

    public Sprite rotate(double angel) {
        ImageView imageView = new ImageView(image_);
        imageView.setRotate(angel);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setViewport(new Rectangle2D(0, 0, getWidth(), getHeight()));
        parameters.setFill(Color.TRANSPARENT);
        return new Sprite(imageView.snapshot(parameters, null));
    }

    public void draw(double x, double y) {
        graphicsContext.drawImage(image_, x, y);
    }


    private void rotate(double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        graphicsContext.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }


    public void drawRotatedImage(double angle, double x, double y) {

        graphicsContext.save(); // saves the current state on stack, including the current transform
        rotate(angle, x + this.getWidth() / 2, y + this.getHeight() / 2);
        graphicsContext.drawImage(this.getImage(), x, y);
        graphicsContext.restore(); // back to original state (before rotation)
    }

    public static void drawGrid(int minX, int minY, int width, int height, int size) {
        for (int i = minX; i <= minX + width; i += size)
            graphicsContext.fillRect(i, minY, 1, height);
        for (int i = minY; i <= minY + height; i += size)
            graphicsContext.fillRect(minX, i, width, 1);
    }


    private static Sprite[] loadFrame(String root, int start, int end, int width, int height) {
        Sprite[] frame = new Sprite[end - start + 1];
        int index = 0;
        for (int i = start; i <= end; i++) {
            frame[index++] = new Sprite(new Image(String.format(root, i), width, height, false, true));
        }
        return frame;
    }

    /*

     |=========================================================
     |Iron Ball Tower
     |=========================================================

     */
    public static Sprite[] ironBallStage = {
            new Sprite(new Image("res/towerTexture/3.png", 64, 70, false, true)),
            new Sprite(new Image("res/towerTexture/6.png", 64, 70, false, true)),
            new Sprite(new Image("res/towerTexture/7.png", 64, 70, false, true))
    };
    public static Sprite[] ironBallTowerUB = {
            new Sprite(new Image("res/towerTexture/1.png", 57, 12, false, true)),
            new Sprite(new Image("res/towerTexture/1.png", 57, 12, false, true)),
            new Sprite(new Image("res/towerTexture/4.png", 57, 12, false, true))
    };
    public static Sprite[] ironBallTowerUF = {
            new Sprite(new Image("res/towerTexture/2.png", 57, 15, false, true)),
            new Sprite(new Image("res/towerTexture/2.png", 57, 15, false, true)),
            new Sprite(new Image("res/towerTexture/5.png", 57, 15, false, true))
    };
    /*
     |=========================================================
     |Fire Ball Tower
     |=========================================================

     */
    public static Sprite[] fireBallStage = {
            new Sprite(new Image("res/towerTexture/12.png", 64, 68, false, true)),
            new Sprite(new Image("res/towerTexture/13.png", 64, 68, false, true)),
            new Sprite(new Image("res/towerTexture/14.png", 64, 68, false, true))
    };
    public static Sprite[] fireBallTowerUB = {
            new Sprite(new Image("res/towerTexture/8.png", 57, 15, false, true)),
            new Sprite(new Image("res/towerTexture/8.png", 57, 15, false, true)),
            new Sprite(new Image("res/towerTexture/10.png", 57, 15, false, true))
    };
    public static Sprite[] fireBallTowerUF = {
            new Sprite(new Image("res/towerTexture/9.png", 57, 22, false, true)),
            new Sprite(new Image("res/towerTexture/9.png", 57, 22, false, true)),
            new Sprite(new Image("res/towerTexture/11.png", 57, 22, false, true))
    };
      /*
     |=========================================================
     |Fire Ball Tower
     |=========================================================

     */

    public static Sprite[][] archerTower = {
            {
                    new Sprite(new Image("res/towerTexture/63.png", 64, 64, false, true)),
                    new Sprite(new Image("res/towerTexture/64.png", 64, 64, false, true)),
                    new Sprite(new Image("res/towerTexture/67.png", 64, 64, false, true))
            },
            {
                    new Sprite(new Image("res/towerTexture/62.png", 64, 64, false, true)),
                    new Sprite(new Image("res/towerTexture/65.png", 64, 64, false, true)),
                    new Sprite(new Image("res/towerTexture/66.png", 64, 64, false, true))
            }
    };
    /*
    |=========================================================
    |Bullet
    |=========================================================

    */

    public static Sprite ironBall = new Sprite(new Image("res/towerTexture/49.png", 20, 20, false, true)).resizeBound(64, 64);
    public static Sprite fireBall = new Sprite(new Image("res/towerTexture/35.png", 20, 45, false, true)).resizeBound(64, 64);
    public static Sprite arrow = new Sprite(new Image("res/towerTexture/68.png")).rotate(90);
    /*

    |=========================================================
    |Obstacle
    |=========================================================

    */

    public static Sprite bush = new Sprite(new Image("res/Obstacle/Bush1.png", 64, 32, true, true));
    public static Sprite stone = new Sprite(new Image("res/Obstacle/Stone1.png", 80, 48, false, true));
    public static Sprite tree = new Sprite(new Image("res/Obstacle/Tree1.png", 96, 112, false, true));


    /*

    |=========================================================
    |Ring
    |=========================================================

    */

    public static Sprite clickCircle = new Sprite(new Image("res/icon/clickCircle.png", 150, 150, false, true));
    public static Sprite[][] buttonOnCircle = {
            {
                    new Sprite(new Image("res/icon/up_off.png", 58, 71, false, true)),
                    new Sprite(new Image("res/icon/up_on.png", 58, 71, false, true)),
                    new Sprite(new Image("res/icon/lock.png", 58, 58, false, true))
            },
            {
                    new Sprite(new Image("res/icon/sell_off.png", 58, 71, false, true)),
                    new Sprite(new Image("res/icon/sell_on.png", 58, 71, false, true)),
            }
    };
    public static Sprite attack_ring = new Sprite((new Image("res/towerTexture/attack_range.png")));
    /*

    |=========================================================
    |EndGame
    |=========================================================

    */
    public static Sprite victory = new Sprite("res/victory.png");
    public static Sprite defeat = new Sprite("res/defeat.png");

    /*

    |=========================================================
    |Graphics
    |=========================================================

    */
    public static Sprite background = null;
    public static Sprite[] room = {
            new Sprite(new Image("res/map1.png")),
            new Sprite(new Image("res/map2.png")),
            new Sprite(new Image("res/map3.png"))
    };

    /*

    |=========================================================
    |   Enemy
    |=========================================================

    */
    public static Sprite[][] batEnemy = {
            loadFrame("res/enemy/Monster 1/Bottom-left/Walk/Version1_Walk_%02d.png", 0, 24, 64, 64),
            loadFrame("res/enemy/Monster 1/Bottom-right/Walk/Version1_Walk_%02d.png", 0, 24, 64, 64),
            loadFrame("res/enemy/Monster 1/Bottom-left/Death2/Version1_Death2_%02d.png", 0, 23, 64, 64),
            loadFrame("res/enemy/Monster 1/Bottom-right/Death2/Version1_Death2_%02d.png", 0, 23, 64, 64)
    };
    public static Sprite[][] normalOrk = {
            loadFrame("res/enemy/Monster 2/Left/WALK/WALK_%03d.png", 0, 6, 43, 32),
            loadFrame("res/enemy/Monster 2/Right/WALK/WALK_%03d.png", 0, 6, 43, 32),
            loadFrame("res/enemy/Monster 2/Left/DIE/DIE_%03d.png", 0, 6, 43, 32),
            loadFrame("res/enemy/Monster 2/Right/DIE/DIE_%03d.png", 0, 6, 43, 32)
    };
    public static Sprite[][] tankOrk = {
            loadFrame("res/enemy/Monster 3/Left/WALK/WALK_%03d.png", 0, 6, 43, 32),
            loadFrame("res/enemy/Monster 3/Right/WALK/WALK_%03d.png", 0, 6, 43, 32),
            loadFrame("res/enemy/Monster 3/Left/DIE/DIE_%03d.png", 0, 6, 43, 32),
            loadFrame("res/enemy/Monster 3/Right/DIE/DIE_%03d.png", 0, 6, 43, 32)
    };
    public static Sprite[][] troll = {
            loadFrame("res/enemy/Monster 4/Left/WALK/WALK_%03d.png", 0, 6, 91, 64),
            loadFrame("res/enemy/Monster 4/Right/WALK/WALK_%03d.png", 0, 6, 91, 64),
            loadFrame("res/enemy/Monster 4/Left/DIE/DIE_%03d.png", 0, 6, 124, 64),
            loadFrame("res/enemy/Monster 4/Right/DIE/DIE_%03d.png", 0, 6, 124, 64)
    };

    /*

    |=========================================================
    |   Gui
    |=========================================================

    */
    public static Sprite mainMenuImg = new Sprite(new Image("res/gui/menu_main.png", Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, false, true));
    public static Sprite start = new Sprite(new Image("res/gui/start.png"));
    public static Sprite chooseLevel = new Sprite(new Image("res/gui/chooseLevel.png"));
    public static Sprite backward = new Sprite(new Image("res/gui/backward.png"));
    public static Sprite forward = new Sprite(new Image("res/gui/forward.png"));
    public static Sprite backtomenu = new Sprite(new Image("res/gui/backToMenu.png"));
    public static Sprite tick = new Sprite(new Image("res/gui/tick.png"));
    public static Sprite[] roomView = {
            new Sprite(new Image("res/map1.png", 250, 250, false, true)),
            new Sprite(new Image("res/map2.png", 250, 250, false, true)),
            new Sprite(new Image("res/map3.png", 250, 250, false, true))
    };

    public static Sprite infoBar = new Sprite(new Image("res/gui/bar.png"));
}
