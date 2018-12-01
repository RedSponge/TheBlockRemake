package com.redsponge.blockremake.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.redsponge.blockremake.TheBlock;
import com.redsponge.blockremake.util.Constants;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = (int) Constants.RESOLUTION.x / 2;
        config.height = (int) Constants.RESOLUTION.y / 2;
        new LwjglApplication(new TheBlock(), config);
    }
}
