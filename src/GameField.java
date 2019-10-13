import Graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameField {
    private int coins;
    private static final int NROW = 12;
    private static final int NCOL = 20;
    private int map[][] = new int[NROW][NCOL];
    private static int tileSize = 128;
    ArrayList<Sprite> tileMap;
    GameField(String path,int themeIndex) {
        loadMapFromFile(new File(path));
        loadSpriteTileMap(themeIndex);
    }

    public void setCoins(int coins) { this.coins = coins; }
    public int getCoins() { return coins; }
    public static int getNCOL() { return NCOL; }
    public static int getNROW() { return NROW; }

    private void loadMapFromFile(File file) {
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println(e.toString());
            return;
        }
        for(int i = 0; i < NROW; i++)
            for (int j = 0; j < NCOL; j++) map[i][j] = sc.nextInt();
    }

   private void loadSpriteTileMap(int i) {
        tileMap = Sprite.listTheme.get(i);
   }
   public void draw(GraphicsContext gc) {
        for(int i = 0; i < 12; i++) {
            for(int j = 0; j < 20; j++) {
                tileMap.get(map[i][j]).draw(gc, j * tileSize, i * tileSize);
                System.out.print(map[i][j] + " ");
            }
            System.out.println("");
        }
   }


}
