package com.redsponge.blockremake.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
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
    private float speedX;

    public PlayerSystem(InputHandle inputHandle) {
        super(Family.all(PlayerComponent.class).get());
        this.inputHandle = inputHandle;
        this.speedX = 0;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BodyComponent body = ComponentMappers.body.get(entity);
        RectangleComponent rect = ComponentMappers.rectangle.get(entity);
        CollideComponent collideComponent = ComponentMappers.collider.get(entity);
        if (inputHandle.jump || jumping) {
            if (collideComponent.floor.touching > 0 && !jumping) {
                startJump(entity);
            } else {
                continueJump(entity);
            }
        }

        if (inputHandle.right) {
            speedX += (Constants.MAX_SPEED.x - speedX) * 0.1f;
        }

        if (inputHandle.left) {
            speedX += (-Constants.MAX_SPEED.x - speedX) * 0.1f;
        }

        body.body.setLinearVelocity(new Vector2(getSpeed(entity), body.body.getLinearVelocity().y));
        Gdx.app.log("[PLAYER]", "" + speedX);
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

    private float getSpeed(Entity e) {
        float mainSpeed = speedX;

        if(onGround(e) && !inputHandle.left && !inputHandle.right) {
            speedX *= Constants.PLAYER_SLIDE_STOP_STRENGTH;
        }
        if(Math.abs(speedX) < Constants.PLAYER_MIN_HORIZONTAL_SPEED) {
            return 0;
        }

        if(Math.abs(speedX) > Constants.MAX_SPEED.x) {
            return Math.signum(speedX) * Constants.MAX_SPEED.x;
        }

        return speedX;
    }

    private boolean onGround(Entity e) {
        CollideComponent collide = ComponentMappers.collider.get(e);
        return collide.floor.touching > 0;
    }

    private void endJump() {
        jumping = false;
    }
}
