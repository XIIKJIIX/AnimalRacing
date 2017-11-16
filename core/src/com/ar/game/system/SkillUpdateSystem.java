package com.ar.game.system;

import com.ar.game.component.Mapper;
import com.ar.game.component.PhysicsComponent;
import com.ar.game.component.SkillComponent;
import com.ar.game.component.TransformComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.google.inject.Inject;

public class SkillUpdateSystem extends IteratingSystem {
    @Inject
    public SkillUpdateSystem() {
        super(Family.all(SkillComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        SkillComponent skill = Mapper.skill.get(entity);
        PhysicsComponent physics = Mapper.physics.get(entity);
        TransformComponent transform = Mapper.transform.get(entity);


        switch (skill.shotType) {
            case BULLET:
                transform.position.x *= skill.acc;
                physics.body.setTransform(transform.position.x, transform.position.y, 0);
                break;
            default:
        }
    }
}
