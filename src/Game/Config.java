package game;

import com.sun.javafx.geom.Vec2d;

public final class Config {

    public static final int LARGE_TILE_SIZE = 128;
    public static final int TILE_SIZE = 64;
    public static final int COL_NUMBER = 20;
    public static final int ROW_NUMBER = 12;


    public static final double CANVAS_WIDTH = COL_NUMBER * TILE_SIZE;
    public static final double CANVAS_HEIGHT = ROW_NUMBER * TILE_SIZE;

    public static final double SCREEN_WIDTH = CANVAS_WIDTH  + 215;
    public static final double SCREEN_HEIGHT = CANVAS_HEIGHT ;
    /*
    |================================================================
    |Enemy Data
    |================================================================
    */
    public static final double HP_BAR_WIDTH = 64;
    public static final double HP_BAR_HEIGHT = 10;

    //0.256 0.32 0.4 0.5 0.512 0.64 0.8 1.0 1.024 1.28 1.6 2.0 2.56 3.2 4.0 5.12 6.4 8.0 12.8 16.0 25.6 32.0 64.0 128.0
    //NormalEnemy
    public static final Vec2d NORMAL_ENEMY_HP_BAR_OFFSET = new Vec2d(32, 32);
    public static final double NORMAL_ENEMY_MAX_HEALTH = 100;
    public static final double NORMAL_ENEMY_SPEED = 1.28;
    public static final double NORMAL_ENEMY_ARMOR = 3;
    public static final int NORMAL_ENEMY_REWARD_COINS = 1;

    //TankerEnemy
    public static final Vec2d TANKER_ENEMY_HP_BAR_OFFSET = new Vec2d(32, 16);
    public static final double TANKER_ENEMY_MAX_HEALTH = 300;
    public static final double TANKER_ENEMY_SPEED = 1.28;
    public static final double TANKER_ENEMY_ARMOR = 3;
    public static final int TANKER_ENEMY_REWARD_COINS = 3;

    //SmallerEnemy
    public static final Vec2d SMALLER_ENEMY_HP_BAR_OFFSET = new Vec2d(32, 16);
    public static final double SMALLER_ENEMY_MAX_HEALTH = 100;
    public static final double SMALLER_ENEMY_SPEED = 1.6;
    public static final double SMALLER_ENEMY_ARMOR = 0;
    public static final int SMALLER_ENEMY_REWARD_COINS = 2;

    //BossEnemy
    public static final Vec2d BOSS_ENEMY_HP_BAR_OFFSET = new Vec2d(32, 16);
    public static final double BOSS_ENEMY_MAX_HEALTH = 500;
    public static final double BOSS_ENEMY_SPEED = 1.28;
    public static final double BOSS_ENEMY_ARMOR = 3;
    public static final int BOSS_ENEMY_REWARD_COINS = 10;

    /*
   |================================================================
   |Bullet Data
   |================================================================
   */
    /* SPEED : small -> faster || big -> slower*/

    public static final long NORMAL_BULLET_STRENGTH = 30;
    public static final double NORMAL_BULLET_SPEED = 15;

    public static final long MACHINE_GUN_BULLET_STRENGTH = 10;
    public static final double MACHINE_GUN_BULLET_SPEED = 30;

    public static final long SNIPER_BULLET_STRENGTH = 200;
    public static final double SNIPER_BULLET_SPEED = 15;


    /*
   |================================================================
   |Tower Data
   |================================================================
   */
    //region Tower
    public static final long NORMAL_TOWER_SPEED = 30;
    public static final double NORMAL_TOWER_RANGE = 3.5 * TILE_SIZE;

    public static final long MACHINE_GUN_TOWER_SPEED = 10;
    public static final double MACHINE_GUN_TOWER_RANGE = 2.5 * TILE_SIZE;

    public static final long SNIPER_TOWER_SPEED = 120;
    public static final double SNIPER_TOWER_RANGE = 4.5 * TILE_SIZE;

}
