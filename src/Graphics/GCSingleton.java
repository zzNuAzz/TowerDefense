package graphics;

import javafx.scene.canvas.GraphicsContext;

public class GCSingleton {
    private static GraphicsContext graphicsContext = null;

    private GCSingleton() {

    }
    public static void setInstance(GraphicsContext graphicsContext) {
        GCSingleton.graphicsContext = graphicsContext;
    }
    public static GraphicsContext getInstance() {
        if(graphicsContext == null) throw new NullPointerException("GraphicsContext is not initialization.");
;       return graphicsContext;
    }
}
