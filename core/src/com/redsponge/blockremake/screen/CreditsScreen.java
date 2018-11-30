package com.redsponge.blockremake.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.redsponge.blockremake.TheBlock;
import com.redsponge.blockremake.assets.Fonts;
import com.redsponge.blockremake.button.SlideInTextButton;
import com.redsponge.blockremake.skins.Skins;
import com.redsponge.blockremake.util.Utils;

public class CreditsScreen extends ScreenAdapter {

    private SpriteBatch batch;
    private ShapeRenderer renderer;
    private FitViewport viewport;

    private Stage stage;

    private Button returnBack;


    @Override
    public void show() {
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();

        viewport = new FitViewport(MenuScreen.WIDTH, MenuScreen.HEIGHT);


        stage = new Stage(viewport, batch);

        returnBack = new TextButton("Return To Menu", Skins.INSTANCE.UI);
        returnBack.setPosition(viewport.getWorldWidth() / 2, 100, Align.center);

        returnBack.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                TheBlock.INSTANCE.setScreen(new MenuScreen());
            }
        });

        stage.addActor(returnBack);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();
        Fonts.INSTANCE.LCD.getData().setScale(0.5f);
        Utils.renderCentered(Fonts.INSTANCE.LCD, batch, 400, "Original Game: Dor C", viewport);
        Utils.renderCentered(Fonts.INSTANCE.LCD, batch, 250, "Remake: Eran G", viewport);
        Fonts.INSTANCE.LCD.getData().setScale(1f);
        batch.end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }
}
