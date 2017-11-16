package com.ar.game.system;

import com.ar.game.component.Mapper;
import com.ar.game.component.PhysicsComponent;
import com.ar.game.component.SkillComponent;
import com.ar.game.component.TransformComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
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

        float yVelocity = physics.body.getLinearVelocity().y;
        float xVelocity = physics.body.getLinearVelocity().x;

        switch (skill.shotType) {
            case BULLET:
                physics.body.setLinearVelocity(
                        MathUtils.lerp(xVelocity, 5F, 0.2F),
                        yVelocity
                );
                break;
            default:
        }
    }
}
