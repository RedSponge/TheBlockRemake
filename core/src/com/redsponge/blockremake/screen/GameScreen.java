package com.redsponge.blockremake.screen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.redsponge.blockremake.assets.ParticlePools;
import com.redsponge.blockremake.components.BodyComponent;
import com.redsponge.blockremake.components.CollideComponent;
import com.redsponge.blockremake.components.RectangleComponent;
import com.redsponge.blockremake.input.InputHandle;
import com.redsponge.blockremake.input.KeyboardInput;
import com.redsponge.blockremake.systems.*;
import com.redsponge.blockremake.util.Constants;
import com.redsponge.blockremake.util.EntityFactory;
import com.redsponge.blockremake.util.Utils;

public class GameScreen extends ScreenAdapter {

    private ShapeRenderer renderer;
    private SpriteBatch batch;
    private FitViewport viewport;
    private Engine engine;
    private Entity player;

    @Override
    public void show() {
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        viewport = new FitViewport(Constants.RESOLUTION.x, Constants.RESOLUTION.y);

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

        Vector2 playerPos = Utils.getWorldPosition(player);

        viewport.getCamera().position.lerp(new Vector3(playerPos, 0), 0.8f);

        viewport.apply();
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        batch.setProjectionMatrix(viewport.getCamera().combined);


        ParticleEffectPool.PooledEffect effect = ParticlePools.INSTANCE.playerFollow.obtain();
        effect.setPosition(playerPos.x, playerPos.y + 20);
        effect.draw(batch, delta);

        engine.update(delta);
//        ParticlePools.INSTANCE.playerFollow.free(effect);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, false);
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
