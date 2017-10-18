package com.ar.game.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.physics.box2d.World;
import com.google.inject.Inject;

public class PhysicsSystem extends EntitySystem {
    private World world;
    float accumlator = 0F;
    private static float TIME_STEP = 1.0F / 300F;
    private static int VELOCITY_ITERATIONS = 6;
    private static int POSITION_ITERATION = 2;

    @Inject
    public PhysicsSystem(World world) {
        this.world = world;
    }

    @Override
    public void update(float deltaTime) {
        float frameTime = Math.min(deltaTime, 0.25F);
        accumlator += frameTime;
        while (accumlator >= TIME_STEP) {
            world.step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATION);
            accumlator -= TIME_STEP;
        }
    }
}
