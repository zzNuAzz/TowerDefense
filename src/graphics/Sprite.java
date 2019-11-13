package graphics;

import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;

public class Sprite {
    private static GraphicsContext graphicsContext = GCSingleton.getInstance();

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
        parameters.setViewport(new Rectangle2D(-getWidth() / x, -getHeight() / y, getWidth() * x, getHeight() * y));
        parameters.setFill(Color.TRANSPARENT);
        return new Sprite(imageView.snapshot(parameters, null));
    }

    public Sprite scale(double xTimes, double yTimes) {
        ImageView imageView = new ImageView(image_);
        imageView.setFitWidth(getWidth() * xTimes);
        imageView.setFitHeight(getHeight() * yTimes);
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
    /*
    |============================================================
    | Theme Sprite                                              |
    |============================================================
    */

    public static ArrayList<ArrayList<Sprite>> listTheme = initTheme();

    private static ArrayList<ArrayList<Sprite>> initTheme() {
        ArrayList<ArrayList<Sprite>> listTheme = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 4; i++) {
                ArrayList<Sprite> theme = new ArrayList<>();
                for (int offsetY = 0; offsetY < 3; offsetY++)
                    for (int offsetX = 0; offsetX < 5; offsetX++)
                        theme.add(SpriteSheet.texture_small.getSprite(offsetY + i * 3, offsetX + j * 5));
                listTheme.add(theme);
            }
        }
        return listTheme;
    }


    /*

    |=========================================================
    |EnemySprite NormalEntity
    |=========================================================

    */

    public static Sprite normalEnemyRight = SpriteSheet.texture_small.getSprite(10, 17).resizeBound(2, 2);
    public static Sprite normalEnemyLeft = SpriteSheet.texture_small.getSprite(10, 17).resizeBound(2, 2).rotate(180);
    public static Sprite normalEnemyUp = SpriteSheet.texture_small.getSprite(10, 17).resizeBound(2, 2).rotate(-90);
    public static Sprite normalEnemyDown = SpriteSheet.texture_small.getSprite(10, 17).resizeBound(2, 2).rotate(90);
    /*

    |=========================================================
    |EnemySprite TankerEnemy
    |=========================================================

    */
    public static Sprite tankerEnemyRight = SpriteSheet.texture_small.getSprite(10, 15).resizeBound(2, 2);
    public static Sprite tankerEnemyLeft = SpriteSheet.texture_small.getSprite(10, 15).resizeBound(2, 2).rotate(180);
    public static Sprite tankerEnemyUp = SpriteSheet.texture_small.getSprite(10, 15).resizeBound(2, 2).rotate(-90);
    public static Sprite tankerEnemyDown = SpriteSheet.texture_small.getSprite(10, 15).resizeBound(2, 2).rotate(90);
    /*

    |=========================================================
    |EnemySprite SmallerEnemy
    |=========================================================

    */
    public static Sprite smallerEnemyRight = SpriteSheet.texture_small.getSprite(10, 18).resizeBound(2, 2);
    public static Sprite smallerEnemyLeft = SpriteSheet.texture_small.getSprite(10, 18).resizeBound(2, 2).rotate(180);
    public static Sprite smallerEnemyUp = SpriteSheet.texture_small.getSprite(10, 18).resizeBound(2, 2).rotate(-90);
    public static Sprite smallerEnemyDown = SpriteSheet.texture_small.getSprite(10, 18).resizeBound(2, 2).rotate(90);
    /*

    |=========================================================
    |EnemySprite BossEnemy
    |=========================================================

    */
    public static Sprite bossEnemyRight = SpriteSheet.texture_small.getSprite(10, 16).scale(2, 2);
    public static Sprite bossEnemyLeft = SpriteSheet.texture_small.getSprite(10, 16).scale(2, 2).rotate(180);
    public static Sprite bossEnemyUp = SpriteSheet.texture_small.getSprite(10, 16).scale(2, 2).rotate(-90);
    public static Sprite bossEnemyDown = SpriteSheet.texture_small.getSprite(10, 16).scale(2, 2).rotate(90);
    /*

    |=========================================================
    |Normal Tower
    |=========================================================

    */
    public static Sprite normalTowerStage = SpriteSheet.texture_small.getSprite(7, 19);
    public static Sprite normalTowerTop = SpriteSheet.texture_small.getSprite(8, 19);
    /*

    |=========================================================
    |Sniper Tower
    |=========================================================

    */
    public static Sprite sniperTowerStage = SpriteSheet.texture_small.getSprite(7, 19);
    public static Sprite sniperTowerTop = SpriteSheet.texture_small.getSprite(10, 19);
    /*

    |=========================================================
    |MachineGun Tower
    |=========================================================

    */
    public static Sprite machineGunTowerStage = SpriteSheet.texture_small.getSprite(7, 19);
    public static Sprite machineGunTowerTop = SpriteSheet.texture_small.getSprite(10, 20);
    /*

    |=========================================================
    |Bullet
    |=========================================================

    */
    public static Sprite normalBullet = SpriteSheet.texture_small.getSprite(11, 19);
    public static Sprite sniperBullet = SpriteSheet.texture_small.getSprite(12, 19).rotate(180);
    public static Sprite machineGunBullet = SpriteSheet.texture_small.getSprite(12, 21).rotate(180);
    /*

    |=========================================================
    |Obstacle
    |=========================================================

    */
    public static Sprite smallBushObstacle = SpriteSheet.texture_small.getSprite(5, 16);
    public static Sprite bigBushObstacle = SpriteSheet.texture_small.getSprite(5, 15);
    public static Sprite starBushObstacle = SpriteSheet.texture_small.getSprite(5, 19);
    public static Sprite smallRockObstacle = SpriteSheet.texture_small.getSprite(5, 20);
    public static Sprite bigRockObstacle = SpriteSheet.texture_small.getSprite(5, 21);

    /*

    |=========================================================
    |EndGame
    |=========================================================

    */
    public static Sprite victory = new Sprite("res/victory.png");
    public static Sprite defeat = new Sprite("res/defeat.png");
}
