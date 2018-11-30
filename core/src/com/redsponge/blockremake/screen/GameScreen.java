package com.redsponge.blockremake.screen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.redsponge.blockremake.components.*;
import com.redsponge.blockremake.input.InputHandle;
import com.redsponge.blockremake.input.KeyboardInput;
import com.redsponge.blockremake.systems.*;
import com.redsponge.blockremake.util.EntityFactory;
import com.redsponge.blockremake.util.Utils;

public class GameScreen extends ScreenAdapter {

    public static final int GAME_WIDTH = 500, GAME_HEIGHT = 500;

    private ShapeRenderer renderer;
    private FitViewport viewport;
    private Engine engine;
    private Entity player;

    @Override
    public void show() {
        renderer = new ShapeRenderer();
        viewport = new FitViewport(GAME_WIDTH, GAME_HEIGHT);

        engine = new Engine();
        engine.addSystem(new RenderingSystem(renderer, viewport));
        PhysicsSystem physicsSystem = new PhysicsSystem(new Vector2(0, -10.0f));
        engine.addSystem(physicsSystem);
        engine.addEntityListener(0, physicsSystem);
        engine.addEntityListener(Family.all(BodyComponent.class, RectangleComponent.class, CollideComponent.class).get(), 1, new BodyBuilderListener());

        InputHandle handle = new InputHandle();
        KeyboardInput input = new KeyboardInput(handle);
        Gdx.input.setInputProcessor(input);
        PlayerSystem playerSystem = new PlayerSystem(handle);

        engine.addSystem(playerSystem);

        player = EntityFactory.createPlayer(new Vector2(250, 250));
        engine.addEntity(player);

        Entity platform = EntityFactory.createStaticPlatform(new Vector2(0, 100), new Vector2(1000, 50));
        engine.addEntity(platform);

        engine.addEntity(
                EntityFactory.createStaticPlatform(new Vector2(100, 100), new Vector2(200, 200))
        );

        engine.addSystem(new PhysicsDebugSystem(viewport, physicsSystem.getWorld()));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.getCamera().position.lerp(new Vector3(Utils.getWorldPosition(player), 0), 0.8f);

        viewport.apply();
        renderer.setProjectionMatrix(viewport.getCamera().combined);

        engine.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, false);
    }
}
