package com.ar.game.system;

import com.ar.game.AnimalRacing;
import com.ar.game.component.*;
import com.ar.game.screen.EndScreen;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.google.inject.Inject;

import java.util.stream.Stream;

public class DyingSystem extends IteratingSystem {

    private OrthographicCamera camera;
    private World world;
    private Engine engine;
    private  SpriteBatch batch;
    private AnimalRacing game;

    @Inject
    public DyingSystem( OrthographicCamera camera, World world, Engine engine, SpriteBatch batch, AnimalRacing game) {
        super(Family.all(PlayerComponent.class).get());
        this.camera = camera;
        this.world = world;
        this.engine = engine;
        this.batch = batch;
        this.game = game;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PlayerComponent player = Mapper.player.get(entity);
        TransformComponent transform = Mapper.transform.get(entity);
        DataComponent data = Mapper.data.get(entity);

        float x = transform.position.x;
        float y = transform.position.y;

        if (x <= (camera.position.x - camera.viewportWidth / 2) || y <= (camera.position.y - camera.viewportHeight / 2) || player.health <= 0) {
            Gdx.app.log(data.name + " Status", "Died");
            SystemHelper.removeEntity(entity, world, engine);
            engine.removeAllEntities();
            System.out.println(engine.getEntities().size());
            game.dispose();
            game.setScreen(AnimalRacing.injector.getInstance(EndScreen.class));
        }
    }

    @Override
    public void update(float deltaTime) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        super.update(deltaTime);
        batch.end();
        camera.update();
    }
}
