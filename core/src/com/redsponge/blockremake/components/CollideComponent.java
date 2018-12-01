package com.redsponge.blockremake.components;

import com.badlogic.ashley.core.Component;

/**
 * File created by Eran G aka RedSponge
 * Project: TheBlockRemake
 */

public class CollideComponent implements Component {

    public CollideInfo floor = new CollideInfo();
    public CollideInfo rightWall = new CollideInfo();
    public CollideInfo leftWall = new CollideInfo();
    public CollideInfo sealing = new CollideInfo();
}
