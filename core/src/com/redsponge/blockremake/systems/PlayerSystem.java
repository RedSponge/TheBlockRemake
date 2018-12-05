package com.redsponge.blockremake.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.redsponge.blockremake.components.BodyComponent;
import com.redsponge.blockremake.components.CollideComponent;
import com.redsponge.blockremake.components.ComponentMappers;
import com.redsponge.blockremake.components.PlayerComponent;
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
        super(Family.all(PlayerComponent.class).get(), Constants.PLAYER_PRIORITY);
        this.inputHandle = inputHandle;
        this.speedX = 0;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BodyComponent body = ComponentMappers.body.get(entity);
        CollideComponent collideComponent = ComponentMappers.collider.get(entity);

        handleJump(entity, collideComponent);
        handleLeftRight(entity, collideComponent);

        body.body.setLinearVelocity(new Vector2(speedX, body.body.getLinearVelocity().y));
        Gdx.app.log("[PLAYER]", "" + speedX);

        if (Utils.getWorldPosition(entity).y < 0) {
            Utils.setWorldPosition(entity, Constants.SPAWN_POSITION);
        }
    }

    private void handleJump(Entity entity, CollideComponent collideComponent) {
        if (inputHandle.jump || jumping) {
            if (collideComponent.floor.touching > 0 && !jumping) {
                startJump(entity);
            } else {
                continueJump(entity);
            }
        }
    }

    /**
     * Handle left and right input and update speed
     *
     * @param entity           The entity
     * @param collideComponent The {@link CollideComponent} pooled from the entity
     */
    private void handleLeftRight(Entity entity, CollideComponent collideComponent) {
        handleLeftRightInput(collideComponent);
        applyHorizontalSpeedMinMax(entity);
    }

    /**
     * Handle left and right input
     *
     * @param collideComponent The {@link CollideComponent} pooled from the entity
     */
    private void handleLeftRightInput(CollideComponent collideComponent) {
        if (inputHandle.right) {
            if (collideComponent.leftWall.touching > 0 && speedX < 0) {
                speedX = 0;
            }
            speedX += (Constants.MAX_SPEED.x - speedX) * 0.1f;
        }
        if (inputHandle.left) {
            if (collideComponent.rightWall.touching > 0 && speedX > 0) {
                speedX = 0;
            }
            speedX += (-Constants.MAX_SPEED.x - speedX) * 0.1f;
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
        if (Utils.secondsSince(jumpStart) > Constants.JUMP_LENGTH / Constants.PHYSICS_SPEED || !inputHandle.jump || !jumping) {
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

    private void applyHorizontalSpeedMinMax(Entity e) {
        if (onGround(e) && !inputHandle.left && !inputHandle.right) {
            speedX *= Constants.PLAYER_SLIDE_STOP_STRENGTH;
        }
        if (Math.abs(speedX) < Constants.PLAYER_MIN_HORIZONTAL_SPEED) {
            speedX = 0;
        }

        if (Math.abs(speedX) > Constants.MAX_SPEED.x) {
            speedX = Math.signum(speedX) * Constants.MAX_SPEED.x;
        }
    }

    /**
     * @param e the entity
     * @return is the entity on ground?
     */
    private boolean onGround(Entity e) {
        CollideComponent collide = ComponentMappers.collider.get(e);
        return collide.floor.touching > 0;
    }

}
