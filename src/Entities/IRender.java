package Entities;

import javafx.scene.canvas.GraphicsContext;

public interface IRender {
    public abstract void update(long t);
    public abstract void draw(GraphicsContext graphicsContext);
}
