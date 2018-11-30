package com.redsponge.blockremake;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.redsponge.blockremake.screen.GameScreen;
import com.redsponge.blockremake.screen.MenuScreen;

public class TheBlock extends Game {


	public static Game INSTANCE;
	private boolean changingScreen;
	private float timePassed;
	private Screen toBeScreen;

	private SpriteBatch batch;
	private Texture tex;
	private ShaderProgram transition;

	@Override
	public void create() {
		ShaderProgram.pedantic = false;


		INSTANCE = this;
		batch = new SpriteBatch();
		transition = new ShaderProgram(Gdx.files.internal("shader/line.vert"), Gdx.files.internal("shader/line.frag"));
		tex = new Texture(Gdx.files.internal("badlogic.jpg"));
		super.setScreen(new GameScreen());

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
