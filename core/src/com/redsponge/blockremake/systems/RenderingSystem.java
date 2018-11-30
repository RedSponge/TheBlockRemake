package com.redsponge.blockremake.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.redsponge.blockremake.components.ColorComponent;
import com.redsponge.blockremake.components.ComponentMappers;
import com.redsponge.blockremake.components.RectangleComponent;

import java.util.Comparator;

public class RenderingSystem extends SortedIteratingSystem {

    private ShapeRenderer shapeRenderer;
    private Comparator<Entity> comparator;
    private FitViewport viewport;

    public RenderingSystem(ShapeRenderer renderer, FitViewport viewport) {
        super(Family.all(RectangleComponent.class, ColorComponent.class).get(), new ZComparator());
        this.shapeRenderer = renderer;
        this.viewport = viewport;
    }

    @Override
    public void update(float deltaTime) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        super.update(deltaTime);
        shapeRenderer.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        RectangleComponent rect = ComponentMappers.rectangle.get(entity);
        ColorComponent color = ComponentMappers.color.get(entity);

        shapeRenderer.setColor(color.color);
        shapeRenderer.rect(rect.position.x-rect.scale.x/2, rect.position.y-rect.scale.y/2, rect.scale.x, rect.scale.y);
    }
}
