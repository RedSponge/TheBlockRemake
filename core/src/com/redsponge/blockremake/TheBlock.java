package com.redsponge.blockremake;

import com.badlogic.gdx.Game;
import com.redsponge.blockremake.screen.MenuScreen;

public class TheBlock extends Game {


    public static Game INSTANCE;

    @Override
    public void create() {
        INSTANCE = this;
        super.setScreen(new MenuScreen());
    }
}
