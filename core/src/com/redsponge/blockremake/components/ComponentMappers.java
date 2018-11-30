package com.redsponge.blockremake.components;

import com.badlogic.ashley.core.ComponentMapper;

public class ComponentMappers {

    public static final ComponentMapper<BodyComponent> body = ComponentMapper.getFor(BodyComponent.class);

    public static final ComponentMapper<ColorComponent> color = ComponentMapper.getFor(ColorComponent.class);

    public static final ComponentMapper<RectangleComponent> rectangle = ComponentMapper.getFor(RectangleComponent.class);

    public static final ComponentMapper<FloorCollideComponent> floorCollide = ComponentMapper.getFor(FloorCollideComponent.class);
}
