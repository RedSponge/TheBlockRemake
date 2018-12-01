package com.redsponge.blockremake;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.redsponge.blockremake.screen.MenuScreen;

public class TheBlock extends Game {


    public static Game INSTANCE;

    @Override
    public void create() {
        ShaderProgram.pedantic = false;


        INSTANCE = this;
        super.setScreen(new MenuScreen());

//		batch.setShader(transition);
//		batch.setShader(transition);
    }

//	@Override
//	public void setScreen(Screen screen) {
//		toBeScreen = screen;
//		timePassed = 0;
//		changingScreen = true;
//	}
}
