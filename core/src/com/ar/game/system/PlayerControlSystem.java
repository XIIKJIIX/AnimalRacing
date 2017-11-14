package com.ar.game.system;

import com.ar.game.AnimalRacing;
import com.ar.game.component.Mapper;
import com.ar.game.component.PhysicsComponent;
import com.ar.game.component.PlayerComponent;
import com.ar.game.component.StateComponent;
import static com.ar.game.component.StateComponent.State.*;
import com.ar.game.handler.KeyboardController;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.google.inject.Inject;

public class PlayerControlSystem extends IteratingSystem {
    KeyboardController controller = AnimalRacing.controller;
    @Inject
    public PlayerControlSystem() {
        super(Family.all(PlayerComponent.class).get());
    }
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        StateComponent state = Mapper.state.get(entity);
        PlayerComponent player = Mapper.player.get(entity);
        PhysicsComponent physics = Mapper.physics.get(entity);

        float yVelocity = physics.body.getLinearVelocity().y;
        float xVelocity = physics.body.getLinearVelocity().x;
        Vector2 worldCenter = physics.body.getWorldCenter();
        // If body going down set to falling state
        if (yVelocity > 0) state.set(FALLING);

        // If body stationary on y axis
        if (yVelocity == 0) {
            // change to normal state if previous state was falling (no mid air jump)
            if (state.get() == FALLING) state.set(NORMAL);

            // set state moving if not falling and moving on x axis
            if (xVelocity != 0) state.set(MOVIING);
        }

        if (controller.KEY_MAP.get(player.leftKey))
            physics.body.setLinearVelocity(
                    MathUtils.lerp(xVelocity, -5F, 0.2F),
                    yVelocity
                );

        if (controller.KEY_MAP.get(player.rightKey))
            physics.body.setLinearVelocity(
                    MathUtils.lerp(xVelocity, 5F, 0.2F),
                    yVelocity
            );

        if (!controller.KEY_MAP.get(player.leftKey) && !controller.KEY_MAP.get(player.rightKey))
            physics.body.setLinearVelocity(
                    MathUtils.lerp(xVelocity, 0F, 0.15F),
                    yVelocity
            );

        if (controller.KEY_MAP.get(player.upKey) && (state.get() == NORMAL || state.get() == MOVIING)) {
            physics.body.applyLinearImpulse(0, 4F, worldCenter.x, worldCenter.y, true);
            state.set(JUMPING);
        }
    }
}
