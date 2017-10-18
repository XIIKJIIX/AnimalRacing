package com.ar.game.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.google.inject.Inject;

public class PhysicsDebugSystem extends EntitySystem {
    private World world;
    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer = new Box2DDebugRenderer();

    @Inject
    public PhysicsDebugSystem(World world, OrthographicCamera camera) {
        this.camera = camera;
        this.world = world;
    }

    @Override
    public void update(float deltaTime) {
        renderer.render(world, camera.combined);
    }
}
