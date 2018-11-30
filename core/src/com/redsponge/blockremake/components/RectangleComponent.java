package com.redsponge.blockremake.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class RectangleComponent implements Component {
    public Vector3 position = new Vector3(0, 0, 0);
    public float rotation = 0.0f;
    public Vector2 scale = new Vector2();
    public boolean isHidden = false;
}
