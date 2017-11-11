package com.ar.game.system;

import com.ar.game.component.*;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.google.inject.Inject;

public class CollisionSystem extends IteratingSystem {

    @Inject
    public CollisionSystem() {
        super(Family.all(CollisionComponent.class, PlayerComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        // Get player collision component
        CollisionComponent collision = Mapper.collision.get(entity);
        PhysicsComponent physics = Mapper.physics.get(entity);
        DataComponent data = Mapper.data.get(entity);
        Entity collidedEntity = collision.getCollidedEntity();
        if (collidedEntity != null) {
            DataComponent colData = Mapper.data.get(collidedEntity);
            TypeComponent type = Mapper.type.get(collidedEntity);
            if (type != null) {
                switch (type.getGroup()) {
                    case TypeComponent.PLAYER:
                        System.out.println(data + " collide with " + colData.name);
                        break;
                    case TypeComponent.SCENERY:
                        System.out.println("Player collide with Scene");
                        break;
                    case TypeComponent.ITEM:
                        System.out.println("Player take with Item");
                        break;
                }
                collision.setCollidedEntity(null);
            }
        }
    }
}
