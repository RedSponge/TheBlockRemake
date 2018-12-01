package com.redsponge.blockremake.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.redsponge.blockremake.components.BodyComponent;
import com.redsponge.blockremake.components.CollideComponent;
import com.redsponge.blockremake.components.ComponentMappers;
import com.redsponge.blockremake.components.RectangleComponent;
import com.redsponge.blockremake.util.Constants;

import static com.redsponge.blockremake.util.Constants.PPM;

/**
 * File created by Eran G aka RedSponge
 * Project: TheBlockRemake
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * This class creates jump detector fixtures
 */
public class BodyBuilderListener implements EntityListener {

    @Override
    public void entityAdded(Entity entity) {
        BodyComponent body = ComponentMappers.body.get(entity);
        RectangleComponent rect = ComponentMappers.rectangle.get(entity);
        CollideComponent collideComponent = ComponentMappers.collider.get(entity);

        FixtureDef collider = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(rect.scale.x / 4 / PPM, 2 / PPM, new Vector2(body.body.getLocalCenter()).add(0, -rect.scale.y / 2 / PPM), 0);
        collider.shape = shape;
        collider.isSensor = true;

        collideComponent.floor.collider = body.body.createFixture(collider);
        collideComponent.floor.collider.setUserData(Constants.COLLIDE_DETECTOR_START + Constants.FLOOR_DETECTOR);
        shape.dispose();

        FixtureDef rightWall = new FixtureDef();
        PolygonShape rightWallShape = new PolygonShape();
        rightWallShape.setAsBox(2 / PPM, rect.scale.y / 2.5f / PPM, new Vector2(body.body.getLocalCenter()).add(rect.scale.x / 2 / PPM, 0), 0);
        rightWall.shape = rightWallShape;
        rightWall.isSensor = true;

        collideComponent.rightWall.collider = body.body.createFixture(rightWall);
        collideComponent.rightWall.collider.setUserData(Constants.COLLIDE_DETECTOR_START + Constants.RIGHT_WALL_DETECTOR);
        rightWallShape.dispose();

        FixtureDef leftWall = new FixtureDef();
        PolygonShape leftWallShape = new PolygonShape();
        leftWallShape.setAsBox(2 / PPM, rect.scale.y / 2.5f / PPM, new Vector2(body.body.getLocalCenter()).add(-rect.scale.x / 2 / PPM, 0), 0);
        leftWall.shape = leftWallShape;
        leftWall.isSensor = true;

        collideComponent.leftWall.collider = body.body.createFixture(leftWall);
        collideComponent.leftWall.collider.setUserData(Constants.COLLIDE_DETECTOR_START + Constants.LEFT_WALL_DETECTOR);

        body.body.setUserData(entity);
    }

    @Override
    public void entityRemoved(Entity entity) {
    }
}
