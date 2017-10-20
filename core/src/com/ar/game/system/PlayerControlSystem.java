package com.ar.game.system;

import com.ar.game.component.Mapper;
import com.ar.game.component.PhysicsComponent;
import com.ar.game.component.PlayerComponent;
import com.ar.game.component.StateComponent;
import com.ar.game.handler.KeyboardController;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.google.inject.Inject;

public class PlayerControlSystem extends IteratingSystem {
    private KeyboardController controller;

    @Inject
    public PlayerControlSystem(KeyboardController controller) {
        super(Family.all(PlayerComponent.class).get());
        this.controller = controller;
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
        if (yVelocity > 0) state.set(StateComponent.STATE_FALLING);

        // If body stationary on y axis
        if (yVelocity == 0) {
            // change to normal state if previous state was falling (no mid air jump)
            if (state.get() == StateComponent.STATE_FALLING) state.set(StateComponent.STATE_NORMAL);

            // set state moving if not falling and moving on x axis
            if (xVelocity != 0) state.set(StateComponent.STATE_MOVING);
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

        if (controller.KEY_MAP.get(player.upKey) && (state.get() == StateComponent.STATE_NORMAL || state.get() == StateComponent.STATE_MOVING)) {
            physics.body.applyLinearImpulse(0, 4F, worldCenter.x, worldCenter.y, true);
            state.set(StateComponent.STATE_JUMPING);
        }
    }
}
