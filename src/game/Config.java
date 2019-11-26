package game;

import com.sun.javafx.geom.Vec2d;

public final class Config {

    //    public static final int LARGE_TILE_SIZE = 128;
    public static final int TILE_SIZE = 64;
//    public static final int COL_NUMBER = 17 * 4;
//    public static final int ROW_NUMBER = 14 * 4;


//    public static final double CANVAS_WIDTH = COL_NUMBER * TILE_SIZE;
//    public static final double CANVAS_HEIGHT = ROW_NUMBER * TILE_SIZE;

    public static final double CANVAS_WIDTH =16 * 4 * 17;
    public static final double CANVAS_HEIGHT = 16 * 4 * 14;

    public static final double SCREEN_WIDTH = CANVAS_WIDTH + 215;
    public static final double SCREEN_HEIGHT = CANVAS_HEIGHT;
    /*
    |================================================================
    |Enemy Data
    |================================================================
    */
    public static final double HP_BAR_WIDTH = 32;
    public static final double HP_BAR_HEIGHT = 5;

    //0.256 0.32 0.4 0.5 0.512 0.64 0.8 1.0 1.024 1.28 1.6 2.0 2.56 3.2 4.0 5.12 6.4 8.0 12.8 16.0 25.6 32.0 64.0 128.0
    //NormalOrk
    public static final Vec2d NORMAL_HP_BAR_OFFSET = new Vec2d(8, -8);
    public static final double NORMAL_MAX_HEALTH = 100;
    public static final double NORMAL_SPEED = 0.64;
    public static final double NORMAL_ARMOR = 3;
    public static final int NORMAL_REWARD_COINS = 2;

    //TankOrk
    public static final Vec2d TANKER_ENEMY_HP_BAR_OFFSET = new Vec2d(8, -8);
    public static final double TANKER_ENEMY_MAX_HEALTH = 400;
    public static final double TANKER_ENEMY_SPEED = 0.64;
    public static final double TANKER_ENEMY_ARMOR = 5;
    public static final int TANKER_ENEMY_REWARD_COINS = 5;

    //BatEnemy
    public static final Vec2d FAST_ENEMY_HP_BAR_OFFSET = new Vec2d(8, -8);
    public static final double FAST_ENEMY_MAX_HEALTH = 100;
    public static final double FAST_ENEMY_SPEED = 1.28;
    public static final double FAST_ENEMY_ARMOR = 0;
    public static final int FAST_ENEMY_REWARD_COINS = 1;

    //Troll
    public static final Vec2d BOSS_ENEMY_HP_BAR_OFFSET = new Vec2d(16, -8);
    public static final double BOSS_ENEMY_MAX_HEALTH = 2000;
    public static final double BOSS_ENEMY_SPEED = 0.64;
    public static final double BOSS_ENEMY_ARMOR = 3;
    public static final int BOSS_ENEMY_REWARD_COINS = 50;

    /*
   |================================================================
   |Bullet Data
   |================================================================
   */
    /* SPEED : small -> faster || big -> slower*/



    public static final long[] IRON_BALL_STRENGTH = {30, 45, 70};
    public static final double IRON_BALL_SPEED = 30;

    public static final long[] FIRE_BALL_STRENGTH = {40, 60, 90};
    public static final double FIRE_BALL_SPEED = 30;

    public static final long[] ARROW_STRENGTH = {30, 45, 70};
    public static final double ARROW_SPEED = 10;

    /*
   |================================================================
   |Tower Data
   |================================================================
   */
    //region Tower

    public static final int[] IRON_TOWER_SPEED = {100, 90, 80};
    public static final double[] IRON_TOWER_RANGE = {3.5 * TILE_SIZE, 3.75 * TILE_SIZE, 4 * TILE_SIZE};
    public static final int[] IRON_TOWER_COST = {20, 40, 80};

    public static final int[] FIRE_BALL_TOWER_SPEED = {150, 135, 120};
    public static final double[] FIRE_BALL_TOWER_RANGE = {3.5 * TILE_SIZE, 3.75 * TILE_SIZE, 4 * TILE_SIZE};
    public static final int[] FIRE_BALL_TOWER_COST = {30, 60, 120};

    public static final int[] ARCHER_TOWER_SPEED = {30, 25, 20};
    public static final double[] ARCHER_TOWER_RANGE = {3.5 * TILE_SIZE, 3.75 * TILE_SIZE, 4 * TILE_SIZE};
    public static final int[] ARCHER_TOWER_COST = {30, 60, 120};

    public static final int BUSH_COST = 20;
    public static final int TREE_COST = 40;
    public static final int STONE_COST = 40;
}
