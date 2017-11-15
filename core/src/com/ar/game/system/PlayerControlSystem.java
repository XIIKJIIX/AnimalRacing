package com.ar.game.system;

import com.ar.game.AnimalRacing;
import com.ar.game.component.*;

import static com.ar.game.component.StateComponent.State.*;
import static com.ar.game.constant.B2Dvars.PPM;

import com.ar.game.constant.SkillMapper;
import com.ar.game.entity.Skill;
import com.ar.game.handler.KeyboardController;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.google.inject.Inject;

public class PlayerControlSystem extends IteratingSystem {
    KeyboardController controller = AnimalRacing.controller;
    private Engine engine;
    private World world;
    @Inject
    public PlayerControlSystem(World world, Engine engine) {
        super(Family.all(PlayerComponent.class).get());
        this.world = world;
        this.engine = engine;
    }
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        StateComponent state = Mapper.state.get(entity);
        PlayerComponent player = Mapper.player.get(entity);
        PhysicsComponent physics = Mapper.physics.get(entity);
        TransformComponent transform = Mapper.transform.get(entity);

        float yVelocity = physics.body.getLinearVelocity().y;
        float xVelocity = physics.body.getLinearVelocity().x;
        Vector2 worldCenter = physics.body.getWorldCenter();
        // If body going down set to falling state
        if (yVelocity > 0) state.set(FALLING);

        // If body stationary on y axis
        if (yVelocity > 0 || (yVelocity < 0) && state.prevState == JUMPING) state.set(JUMPING);
        else if (yVelocity < 0) state.set(FALLING);
        else if (xVelocity != 0) state.set(MOVING);
        else state.set(NORMAL);

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

        if (controller.KEY_MAP.get(player.upKey) && (state.get() == NORMAL || state.get() == MOVING)) {
            physics.body.applyLinearImpulse(0, 4F, worldCenter.x, worldCenter.y, true);
            state.set(JUMPING);
        }

        if (controller.KEY_MAP.get(player.castKey)) {
            Skill skill = SkillMapper.map(player.orbs);
            skill.setTransform(new Vector2(transform.position.x + (4f/PPM), transform.position.y));
            engine.addEntity((Entity) skill);
        }

        state.stateTimer = state.state == state.prevState ? state.stateTimer + deltaTime: 0;
        state.prevState = state.state;
    }
}
