package com.ar.game.system;

import com.ar.game.component.*;
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
                float speed = skill.speed;
                if (!skill.isRight) speed = -speed;
                physics.body.setLinearVelocity(
                        MathUtils.lerp(xVelocity, speed, skill.acc),
                        yVelocity
                );
                break;
            default:
        }
    }
}
