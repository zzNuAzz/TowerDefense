package Graphics;

import Entities.IRender;
import javafx.scene.canvas.GraphicsContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BackGround implements IRender {
    private static final int NROW = 12;
    private static final int NCOL = 20;
    private int backGround[][] = new int[NROW][NCOL];
    public static final int TILE_SIZE = 128;
    ArrayList<Sprite> tileMap;

    public BackGround(String path, int themeIndex) {
        loadMapFromFile(new File(path));
        loadSpriteTileMap(themeIndex);
    }

    public int getTiles(int i, int j) {
        if(i < 0 || i >= backGround.length) return Integer.MAX_VALUE;
        if(j < 0 || j >= backGround[i].length) return Integer.MAX_VALUE;
        return backGround[i][j];
    }

    public static int getNCOL() {
        return NCOL;
    }

    public static int getNROW() {
        return NROW;
    }

    private void loadMapFromFile(File file) {
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println(e.toString());
            return;
        }
        for (int i = 0; i < NROW; i++)
            for (int j = 0; j < NCOL; j++) backGround[i][j] = sc.nextInt();
        sc.close();
    }

    private void loadSpriteTileMap(int i) {
        tileMap = Sprite.listTheme.get(i);
    }

    @Override
    public void update(long t) {

    }

    @Override
    public void draw(GraphicsContext gc) {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 20; j++) {
                tileMap.get(backGround[i][j]).draw(gc, j * TILE_SIZE, i * TILE_SIZE);
                gc.fillText(String.valueOf(backGround[i][j]), j * TILE_SIZE +30, i * TILE_SIZE+30);
            }
        }
    }
}
