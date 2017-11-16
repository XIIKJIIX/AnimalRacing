package com.ar.game.system;

import com.ar.game.component.*;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;
import com.google.inject.Inject;

public class CleanupSystem extends IteratingSystem {
    private OrthographicCamera camera;
    private World world;
    private Engine engine;
    @Inject
    public CleanupSystem(OrthographicCamera camera, World world, Engine engine) {
        super(Family.all(SkillComponent.class).get());
        this.camera = camera;
        this.world = world;
        this.engine = engine;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transform = Mapper.transform.get(entity);
        PhysicsComponent physics = Mapper.physics.get(entity);
        DataComponent data = Mapper.data.get(entity);

        float x = transform.position.x;
        float y = transform.position.y;

        if (x <= (camera.position.x - camera.viewportWidth / 2) || y <= (camera.position.y - camera.viewportHeight / 2) || x >= (camera.position.x + camera.viewportWidth / 2)) {
            System.out.println("clear "+ data.name);
            world.destroyBody(physics.body);
            entity.removeAll();
            engine.removeEntity(entity);
        }
    }
}
