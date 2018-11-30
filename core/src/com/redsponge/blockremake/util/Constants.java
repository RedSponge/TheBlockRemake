package com.redsponge.blockremake.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * File created by Eran G aka RedSponge
 * Project: TheBlockRemake
 */

public class Constants {

    public static final float PPM = 20.0f;

    public static final String JUMP_DETECTOR = "jumpDetector";
    public static final String ENTITY = "entity";

    public static final float JUMP_LENGTH = 0.15f;

    public static final Vector2 MAX_SPEED = new Vector2(10, 10);
    public static final Vector2 PLAYER_SCALE = new Vector2(20, 20);
    public static final Color PLAYER_COLOR = Color.GREEN;

    public static final Color PLATFORM_COLOR_STATIC = Color.PURPLE;

    public static final float PLAYER_SLIDE_STOP_STRENGTH = 0.9f; // Speed is multiplied by this number when sliding on ground
    public static final float PLAYER_MIN_HORIZONTAL_SPEED = 0.1f; // Absolute value of lowest speed, when goes below that reverts to 0 speed
}
