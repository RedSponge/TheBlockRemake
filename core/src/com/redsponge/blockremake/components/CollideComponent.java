package com.redsponge.blockremake.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * File created by Eran G aka RedSponge
 * Project: TheBlockRemake
 */

public class CollideComponent implements Component {

    public CollideInfo floor;
    public CollideInfo rightWall;
    public CollideInfo leftWall;
    public CollideInfo sealing;
}
