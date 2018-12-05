package com.redsponge.blockremake.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * File created by Eran G aka RedSponge
 * Project: TheBlockRemake
 */

public class Constants {

    public static final float PPM = 20.0f;

    public static final String COLLIDE_DETECTOR_START = "collide";
    public static final String FLOOR_DETECTOR = "floor";
    public static final String RIGHT_WALL_DETECTOR = "rightWall";
    public static final String ENTITY = "entity";

    public static final float JUMP_LENGTH = 0.15f;

    public static final Vector2 MAX_SPEED = new Vector2(10, 10);
    public static final Vector2 PLAYER_SCALE = new Vector2(20, 40);
    public static final Color PLAYER_COLOR = Color.BLUE;

    public static final Color PLATFORM_COLOR_STATIC = Color.GREEN;

    public static final float PLAYER_SLIDE_STOP_STRENGTH = 0.9f; // Speed is multiplied by this number when sliding on ground
    public static final float PLAYER_MIN_HORIZONTAL_SPEED = 0.1f; // Absolute value of lowest speed, when goes below that reverts to 0 speed
    public static final String LEFT_WALL_DETECTOR = "leftWall";

    public static final Vector2 RESOLUTION = new Vector2(1280, 720);

    public static final float PHYSICS_SPEED = 1f;


    public static final int PLAYER_PRIORITY = 1;
    public static final int PHYSICS_PRIORITY = 2;
    public static final int RENDERING_PRIORITY = 0;
    public static final Vector2 SPAWN_POSITION = new Vector2(250, 250);
}
