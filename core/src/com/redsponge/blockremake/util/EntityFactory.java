package com.redsponge.blockremake.util;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.redsponge.blockremake.components.BodyComponent;
import com.redsponge.blockremake.components.ColorComponent;
import com.redsponge.blockremake.components.CollideComponent;
import com.redsponge.blockremake.components.PlayerComponent;
import com.redsponge.blockremake.components.RectangleComponent;

public final class EntityFactory {

    /**
     * Utility for creating
     * @param pos starting position
     * @param scl size of entity
     * @param color entity color
     * @param specialComponents stuff like {@link com.redsponge.blockremake.components.BodyComponent}
     * @return the entity
     */
    @SafeVarargs
    public static Entity createWorldEntity(Vector2 pos, Vector2 scl, Color color, Class<? extends Component>... specialComponents) {
        Entity entity = new Entity();
        RectangleComponent rectangleComponent = new RectangleComponent();
        rectangleComponent.position.set(new Vector3(pos, 0));
        rectangleComponent.scale.set(scl);

        entity.add(rectangleComponent);

        ColorComponent colorComponent = new ColorComponent();
        colorComponent.color = color;

        entity.add(colorComponent);

        for(Class<? extends Component> component : specialComponents) {
            try {
                Component instance = component.newInstance();
                entity.add(instance);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return entity;
    }

    public static Entity createPlayer(Vector2 position) {
        return createWorldEntity(position, Constants.PLAYER_SCALE, Constants.PLAYER_COLOR, PlayerComponent.class, BodyComponent.class, CollideComponent.class);
    }

    public static Entity createStaticPlatform(Vector2 position, Vector2 scl) {
        Entity entity = createWorldEntity(position, scl, Constants.PLATFORM_COLOR_STATIC);
        BodyComponent body = new BodyComponent();
        body.type = BodyType.StaticBody;
        entity.add(body);

        return entity;
    }
}
