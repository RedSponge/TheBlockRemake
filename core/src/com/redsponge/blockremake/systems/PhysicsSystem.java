package com.redsponge.blockremake.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.redsponge.blockremake.components.BodyComponent;
import com.redsponge.blockremake.components.ComponentMappers;
import com.redsponge.blockremake.components.RectangleComponent;
import com.redsponge.blockremake.util.Constants;

/**
 * File created by Eran G aka RedSponge
 * Project: TheBlockRemake
 */

public class PhysicsSystem extends IteratingSystem implements EntityListener {

    private World world;

    public PhysicsSystem(Vector2 gravity) {
        super(Family.all(BodyComponent.class, RectangleComponent.class).get());
        world = new World(gravity, true);
        world.setContactListener(new ContactHandler());
    }

    @Override
    public void update(float deltaTime) {
        world.step(deltaTime, 5, 5);
        super.update(deltaTime);
    }

    @Override
    public void entityAdded(Entity entity) {
        BodyComponent component = ComponentMappers.body.get(entity);
        RectangleComponent rect = ComponentMappers.rectangle.get(entity);
        BodyDef def = new BodyDef();
        def.type = component.type;
        def.position.set(rect.position.x / Constants.PPM, rect.position.y / Constants.PPM);
        component.body = world.createBody(def);
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(rect.scale.x / 2 / Constants.PPM, rect.scale.y / 2 / Constants.PPM);
        fdef.shape = shape;
        component.body.createFixture(fdef).setUserData(Constants.ENTITY);
        shape.dispose();
    }

    @Override
    public void entityRemoved(Entity entity) {
        BodyComponent body = ComponentMappers.body.get(entity);
        world.destroyBody(body.body);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BodyComponent component = ComponentMappers.body.get(entity);
        RectangleComponent rect = ComponentMappers.rectangle.get(entity);

        rect.position.set(component.body.getPosition().scl(Constants.PPM), 0);
        rect.rotation = component.body.getAngle() * MathUtils.radiansToDegrees;
    }

    public World getWorld() {
        return world;
    }
}
