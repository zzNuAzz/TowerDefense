package gui;

import com.sun.javafx.geom.Rectangle;
import entities.GameEntity;
import entities.Saleable;
import entities.Upgradeable;
import entities.unmovableEntity.GameTile;
import entities.unmovableEntity.towers.Tower;
import graphics.Sprite;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class ClickCircle extends GameEntity {
    private double scale_;
    private GameTile caller;

    public ClickCircle() {
        this.scale_ = 0;
        caller = null;
    }

    private final Rectangle buttonLeftBound = new Rectangle();
    private int leftBT = 0;
    private final Rectangle buttonRightBound = new Rectangle();
    private int rightBT = 0;

    public void onClick(double x, double y, GameTile caller) {
        if (this.caller == null) {
            if (caller == null) return;
            this.caller = caller;
            if (!(caller instanceof Upgradeable) || !((Upgradeable) caller).canUpgrade()) leftBT = 2; // lock
            scale_ = 0;
            xPos_ = caller.getXPos() + caller.getSizeX_() / 2;
            yPos_ = caller.getYPos() + caller.getSizeY_() / 2;
            buttonLeftBound.setBounds(((int) (xPos_ - Sprite.clickCircle.getWidth() * 0.65d)), ((int) (yPos_ - Sprite.clickCircle.getHeight() * 0.25)), (int) Sprite.buttonOnCircle[0][leftBT].getWidth(), (int) Sprite.buttonOnCircle[0][leftBT].getHeight());
            buttonRightBound.setBounds(((int) (xPos_ + Sprite.clickCircle.getWidth() * 0.3d)), ((int) (yPos_ - Sprite.clickCircle.getHeight() * 0.25d)), (int) Sprite.buttonOnCircle[0][rightBT].getWidth(), (int) Sprite.buttonOnCircle[0][rightBT].getHeight());

        } else {
            if (buttonLeftBound.contains((int) x, (int) y)) fireLeft();
            if (buttonRightBound.contains((int) x, (int) y)) fireRight();
            this.caller = null;
        }
    }

    public void onHover(double x, double y) {
        if ((caller instanceof Upgradeable) && ((Upgradeable) caller).canUpgrade())
            leftBT = (buttonLeftBound.contains((int) x, (int) y)) ? 1 : 0;
        rightBT = (buttonRightBound.contains((int) x, (int) y)) ? 1 : 0;
    }

    private void fireLeft() {
        if (caller instanceof Upgradeable) ((Upgradeable) caller).upgrade();
    }

    public void fireRight() {
        if (caller instanceof Saleable) ((Saleable) caller).sell();
    }

    public void close() {
        caller = null;
    }

    @Override
    public void update(long t) {
    }


    @Override
    public void draw() {
        if (caller != null) {
            if (scale_ < 1) scale_ += 0.1;
        } else scale_ -= 0.1;
        if (scale_ < 0) return;
        Sprite circle = (scale_ < 1 ? Sprite.clickCircle.scale(scale_ * Sprite.clickCircle.getWidth(), scale_ * Sprite.clickCircle.getHeight()) : Sprite.clickCircle);
        circle.draw(xPos_ - circle.getWidth() * 0.5d, yPos_ - circle.getHeight() * 0.5d);

        Sprite upgrade = (scale_ < 1 ? Sprite.buttonOnCircle[0][leftBT].scale(scale_, scale_) : Sprite.buttonOnCircle[0][leftBT]);
        upgrade.draw(xPos_ - circle.getWidth() * 0.65d, yPos_ - circle.getHeight() * 0.25d);

        Sprite sell = (scale_ < 1 ? Sprite.buttonOnCircle[1][rightBT].scale(scale_, scale_) : Sprite.buttonOnCircle[1][rightBT]);
        sell.draw(xPos_ + circle.getWidth() * 0.3d, yPos_ - circle.getHeight() * 0.25d);

        if (caller instanceof Upgradeable && leftBT != 2 && Math.round(scale_) == 1) {
            Sprite.getGC().setFill(Color.YELLOW);
            Sprite.getGC().setFont(new Font(12));
            Sprite.getGC().fillText(String.valueOf(((Upgradeable) caller).getUpCost()), xPos_ - 75, yPos_ + 28);
        }

        if (caller instanceof Saleable && Math.round(scale_) == 1) {
            Sprite.getGC().setFill(Color.YELLOW);
            Sprite.getGC().setFont(new Font(12));
            Sprite.getGC().fillText(String.valueOf(((Saleable) caller).getSell()), xPos_ + 70, yPos_ + 28);
        }

        if (caller instanceof Tower) ((Tower) caller).drawRange();

    }
}
