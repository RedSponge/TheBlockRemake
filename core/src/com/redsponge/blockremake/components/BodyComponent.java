package com.redsponge.blockremake.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class BodyComponent implements Component {

    public Body body;
    public BodyType type = BodyType.DynamicBody;

}
