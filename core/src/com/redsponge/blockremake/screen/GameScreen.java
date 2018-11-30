package com.redsponge.blockremake.screen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.redsponge.blockremake.components.*;
import com.redsponge.blockremake.input.InputHandle;
import com.redsponge.blockremake.input.KeyboardInput;
import com.redsponge.blockremake.systems.*;

public class GameScreen extends ScreenAdapter {

    public static final int GAME_WIDTH = 500, GAME_HEIGHT = 500;

    private ShapeRenderer renderer;
    private FitViewport viewport;
    private Engine engine;

    @Override
    public void show() {
        renderer = new ShapeRenderer();
        viewport = new FitViewport(GAME_WIDTH, GAME_HEIGHT);

        engine = new Engine();
        engine.addSystem(new RenderingSystem(renderer, viewport));
        PhysicsSystem physicsSystem = new PhysicsSystem(new Vector2(0, -10.0f));
        engine.addSystem(physicsSystem);
        engine.addEntityListener(0, physicsSystem);
        engine.addEntityListener(Family.all(BodyComponent.class, RectangleComponent.class, FloorCollideComponent.class).get(), 1, new BodyBuilderListener());

        InputHandle handle = new InputHandle();
        KeyboardInput input = new KeyboardInput(handle);
        Gdx.input.setInputProcessor(input);
        PlayerSystem playerSystem = new PlayerSystem(handle);

        engine.addSystem(playerSystem);

        Entity entity = new Entity();
        ColorComponent colorComponent = new ColorComponent();
        colorComponent.color = Color.GREEN;
        RectangleComponent component = new RectangleComponent();
        component.position.set(250, 250, 0);
        component.scale.set(20, 20);

        entity.add(colorComponent);
        entity.add(component);
        entity.add(new BodyComponent());
        entity.add(new PlayerComponent());
        entity.add(new FloorCollideComponent());

        engine.addEntity(entity);

        Entity platform = new Entity();
        ColorComponent colorComponent1 = new ColorComponent();
        colorComponent1.color = Color.PURPLE;
        platform.add(colorComponent1);
        RectangleComponent rectangleComponent = new RectangleComponent();
        rectangleComponent.position.set(0, 100, 0);
        rectangleComponent.scale.set(1000, 50);
        platform.add(rectangleComponent);
        BodyComponent body = new BodyComponent();
        body.type = BodyDef.BodyType.StaticBody;
        platform.add(body);

        engine.addEntity(platform);

        engine.addSystem(new PhysicsDebugSystem(viewport, physicsSystem.getWorld()));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();
        renderer.setProjectionMatrix(viewport.getCamera().combined);

        engine.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }
}
