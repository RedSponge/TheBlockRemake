package com.redsponge.blockremake.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.redsponge.blockremake.TheBlock;
import com.redsponge.blockremake.button.SlideInTextButton;
import com.redsponge.blockremake.skins.Skins;
import com.redsponge.blockremake.util.Constants;

public class MenuScreen extends ScreenAdapter {

    private SpriteBatch batch;
    private ShapeRenderer renderer;
    private ShaderProgram program;

    private Stage stage;
    private FitViewport viewport;

    private Button start;
    private Button instructions;
    private Button credits;
    private Button exit;

    private Skin skin;
    private int counter;

    private Texture tex;

    @Override
    public void show() {
        batch = new SpriteBatch();

        program = new ShaderProgram(Gdx.files.internal("shader/line.vert"), Gdx.files.internal("shader/line.frag"));
        batch.setShader(program);

        renderer = new ShapeRenderer();
        viewport = new FitViewport(Constants.RESOLUTION.x, Constants.RESOLUTION.y);

        skin = Skins.INSTANCE.UI;

        stage = new Stage(viewport, batch);

        start = new SlideInTextButton("Start", skin, (int) Constants.RESOLUTION.x, 300, 2, 200);

        start.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                TheBlock.INSTANCE.setScreen(new GameScreen());
            }
        });

        instructions = new SlideInTextButton("Instructions", skin, (int) Constants.RESOLUTION.x, 240, 2, 200);
        credits = new SlideInTextButton("Credits", skin, (int) Constants.RESOLUTION.x, 180, 2, 200);

        credits.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                TheBlock.INSTANCE.setScreen(new CreditsScreen());
            }
        });

        exit = new SlideInTextButton("Exit", skin, (int) Constants.RESOLUTION.x, 120, 2, 200);

        exit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        Gdx.input.setInputProcessor(stage);
        counter = 0;

        tex = new Texture(Gdx.files.internal("badlogic.jpg"));
    }

    @Override
    public void render(float delta) {
        if (counter == 0) {
            stage.addActor(start);
        } else if (counter == 30) {
            stage.addActor(instructions);
        } else if (counter == 60) {
            stage.addActor(credits);
        } else if (counter == 90) {
            stage.addActor(exit);
        }

        Gdx.gl.glClearColor(0, 0, 0, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(new Color(0.8f, 0.8f, 0.8f, 1.0f));
        renderer.rect(0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        renderer.end();

        stage.act(delta);
        stage.draw();

        counter++;
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }
}
