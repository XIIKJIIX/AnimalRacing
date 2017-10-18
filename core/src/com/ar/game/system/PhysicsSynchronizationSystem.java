package com.ar.game.system;

import com.ar.game.component.Mapper;
import com.ar.game.component.PhysicsComponent;
import com.ar.game.component.TransformComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.Body;
import com.google.inject.Inject;

public class PhysicsSynchronizationSystem extends IteratingSystem {

    @Inject
    public PhysicsSynchronizationSystem() {
        super(Family.all(
                TransformComponent.class,
                PhysicsComponent.class
        ).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PhysicsComponent physicsComponent = Mapper.physics.get(entity);
        TransformComponent transformComponent = Mapper.transform.get(entity);

        Body body = physicsComponent.body;
        transformComponent.position.set(body.getPosition());
        transformComponent.angelRadiant = body.getAngle();
    }


}
