package com.ar.game.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public class CollisionComponent implements Component {
    private Entity collidedEntity;

    public Entity getCollidedEntity() {
        return collidedEntity;
    }

    public void setCollidedEntity(Entity collidedEntity) {
        this.collidedEntity = collidedEntity;
    }
}
