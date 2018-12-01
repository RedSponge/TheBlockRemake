package com.redsponge.blockremake.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.redsponge.blockremake.components.CollideComponent;
import com.redsponge.blockremake.components.CollideInfo;
import com.redsponge.blockremake.components.ComponentMappers;
import com.redsponge.blockremake.util.Constants;

/**
 * File created by Eran G aka RedSponge
 * Project: TheBlockRemake
 */

public class ContactHandler implements ContactListener {

    private static final String TAG = "[Contact Handler]";

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (isCollider(fixA) || isCollider(fixB)) {
            collideAction(fixA, fixB, true);
        }
    }

    /**
     * When any of the contact fixtures is identified as a collider this method is called.
     *
     * @param fixA the first fixture
     * @param fixB the second fixture
     * @param add  increment objects touched counter (when starting contact) or decrement it
     */
    private void collideAction(Fixture fixA, Fixture fixB, boolean add) {
        Fixture contacter = isCollider(fixA) ? fixA : fixB;
        Fixture other = (contacter == fixA) ? fixB : fixA;

        String collideId = ((String) contacter.getUserData()).substring(Constants.COLLIDE_DETECTOR_START.length());
        CollideComponent component = ComponentMappers.collider.get((Entity) contacter.getBody().getUserData());

        CollideInfo info;
        switch (collideId) {
            case Constants.FLOOR_DETECTOR:
                info = component.floor;
                break;
            case Constants.RIGHT_WALL_DETECTOR:
                info = component.rightWall;
                break;
            case Constants.LEFT_WALL_DETECTOR:
                info = component.leftWall;
                break;
            default:
                Gdx.app.error(TAG, "Couldn't identify collideId \"" + collideId + "\". not incrementing!");
                return;
        }

        if (add) info.touching++;
        else info.touching--;
    }

    public boolean isCollider(Fixture fix) {
        try {
            return ((String) fix.getUserData()).startsWith(Constants.COLLIDE_DETECTOR_START);
        } catch (ClassCastException e) {
            Gdx.app.error(TAG, "Couldn't cast user data of type " + fix.getClass().getSimpleName() + " to string!");
            return false;
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (isCollider(fixA) || isCollider(fixB)) {
            collideAction(fixA, fixB, false);
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
