package com.redsponge.blockremake.systems;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * File created by Eran G aka RedSponge
 * Project: TheBlockRemake
 */

public class PhysicsDebugSystem extends EntitySystem {

    private Box2DDebugRenderer renderer;
    private World world;
    private Viewport viewport;

    public PhysicsDebugSystem(Viewport viewport, World world) {
        this.viewport = viewport;
        this.world = world;
        renderer = new Box2DDebugRenderer();
    }

    @Override
    public void update(float deltaTime) {
        renderer.render(world, viewport.getCamera().combined);
    }
}
