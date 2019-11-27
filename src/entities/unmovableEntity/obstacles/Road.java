package entities.unmovableEntity.obstacles;


import entities.unmovableEntity.GameTile;
import graphics.Sprite;

public class Road extends GameTile {
    @Override
    public void update(long t) {

    }

    @Override
    public void draw() {
        Sprite.tree.draw(xPos_, yPos_);
    }
}
