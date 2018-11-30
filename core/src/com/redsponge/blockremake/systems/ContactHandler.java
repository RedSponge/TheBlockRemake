package com.redsponge.blockremake.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.*;
import com.redsponge.blockremake.components.ComponentMappers;
import com.redsponge.blockremake.util.Constants;

/**
 * File created by Eran G aka RedSponge
 * Project: TheBlockRemake
 */

public class ContactHandler implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData().equals(Constants.JUMP_DETECTOR) || fixB.getUserData().equals(Constants.JUMP_DETECTOR)) {
            Fixture contacter = fixA.getUserData().equals(Constants.JUMP_DETECTOR) ? fixA : fixB;
            Fixture other = contacter == fixA ? fixB : fixA;

            ComponentMappers.collider.get((Entity) contacter.getBody().getUserData()).floor.touching++;
            System.out.println("FLOOR TOUCH");
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData().equals(Constants.JUMP_DETECTOR) || fixB.getUserData().equals(Constants.JUMP_DETECTOR)) {
            Fixture contacter = fixA.getUserData().equals(Constants.JUMP_DETECTOR) ? fixA : fixB;
            Fixture other = contacter == fixA ? fixB : fixA;

            ComponentMappers.collider.get((Entity) contacter.getBody().getUserData()).floor.touching--;
            System.out.println("FLOOR LEAVE");
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
