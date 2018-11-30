package com.redsponge.blockremake.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.redsponge.blockremake.components.*;
import com.redsponge.blockremake.input.InputHandle;
import com.redsponge.blockremake.util.Constants;
import com.redsponge.blockremake.util.Utils;

/**
 * File created by Eran G aka RedSponge
 * Project: TheBlockRemake
 */

public class PlayerSystem extends IteratingSystem {

    private InputHandle inputHandle;
    private boolean jumping;
    private long jumpStart;

    public PlayerSystem(InputHandle inputHandle) {
        super(Family.all(PlayerComponent.class).get());
        this.inputHandle = inputHandle;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BodyComponent body = ComponentMappers.body.get(entity);
        RectangleComponent rect = ComponentMappers.rectangle.get(entity);
        FloorCollideComponent floorCollideComponent = ComponentMappers.floorCollide.get(entity);
        if (inputHandle.jump || jumping) {
            if (floorCollideComponent.floorsTouched > 0 && !jumping) {
                startJump(entity);
            } else {
                continueJump(entity);
            }
        }

        boolean move = false;
        if (inputHandle.right) {
            body.body.applyLinearImpulse(
                    new Vector2((Constants.MAX_SPEED.x - body.body.getLinearVelocity().x) * 0.1f, 0)
                    , body.body.getWorldCenter(), true);
            move = true;
        }

        if (inputHandle.left) {
            body.body.applyLinearImpulse(
                    new Vector2((-Constants.MAX_SPEED.x - body.body.getLinearVelocity().x) * 0.1f, 0)
                    , body.body.getWorldCenter(), true);
            move = true;
        }

    }


    private void startJump(Entity entity) {
        if (!jumping) {
            jumping = true;
            jumpStart = System.nanoTime();
            BodyComponent body = ComponentMappers.body.get(entity);
        }
        continueJump(entity);
    }

    private void continueJump(Entity entity) {
        if (Utils.secondsSince(jumpStart) > Constants.JUMP_LENGTH || !inputHandle.jump || !jumping) {
            endJump();
        } else {
            BodyComponent bodyComponent = ComponentMappers.body.get(entity);
            float timeLeft = Constants.JUMP_LENGTH - Utils.secondsSince(jumpStart);
            bodyComponent.body.applyLinearImpulse(new Vector2(0, 10f * timeLeft), bodyComponent.body.getWorldCenter(), true);
        }
    }

    private void endJump() {
        jumping = false;
    }
}
