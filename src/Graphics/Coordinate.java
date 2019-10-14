package Graphics;

import com.sun.javafx.geom.Vec2d;
import com.sun.javafx.geom.Vec2f;
import javafx.scene.canvas.GraphicsContext;
public class Coordinate {
    public static int TILE_SIZE = 128;
    public static Vec2d getTiles(int x, int y, int size) {
        return new Vec2d(x / size,y /size);
    }
    public static Vec2d getTiles(int x, int y) {
        return getTiles(x, y, TILE_SIZE);
    }
    public static Vec2f getPixels(int x, int y, int size) {
        return new Vec2f(x*size, y*size);
    }
    public static Vec2f getPixels(int x, int y) { return new Vec2f(x * TILE_SIZE, y*TILE_SIZE); }
    public static int getTiles(int i) {
        return (int)(i/TILE_SIZE);
    }
    public static int getPixels(int i) { return i * TILE_SIZE; }
    public static void drawGrid(GraphicsContext graphicsContext, int x, int y, int w, int h, int size) {
        for(int i = x; i <= x+w; i+= size)
            graphicsContext.fillRect(i, y,1,h);
        for(int i = y; i <= y+h; i+= size)
            graphicsContext.fillRect(x, i, w,1);
    }
}
