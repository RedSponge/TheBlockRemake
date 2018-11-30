package com.redsponge.blockremake.systems;

import com.badlogic.ashley.core.Entity;
import com.redsponge.blockremake.components.ComponentMappers;

import java.util.Comparator;

public class ZComparator implements Comparator<Entity> {

    @Override
    public int compare(Entity o1, Entity o2) {
        return (int) Math.signum(ComponentMappers.rectangle.get(o1).position.z) - (int) Math.signum(ComponentMappers.rectangle.get(o2).position.z);
    }
}
