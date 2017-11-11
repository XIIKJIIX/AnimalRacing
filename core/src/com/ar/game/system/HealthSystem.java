package com.ar.game.system;

import com.ar.game.component.DataComponent;
import com.ar.game.component.Mapper;
import com.ar.game.component.PlayerComponent;
import com.ar.game.component.TransformComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.inject.Inject;

public class HealthSystem extends IteratingSystem {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private AssetManager manager;
    private Texture healthBar;

    @Inject
    public HealthSystem(OrthographicCamera camera, SpriteBatch batch, AssetManager manager) {
        super(Family.all(PlayerComponent.class).get());
        this.batch = batch;
        this.camera = camera;
        this.manager = manager;
        healthBar = manager.get("blank.png", Texture.class);
    }
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transform = Mapper.transform.get(entity);
        PlayerComponent player = Mapper.player.get(entity);
        DataComponent data = Mapper.data.get(entity);
        batch.draw(healthBar, transform.position.x - data.width, transform.position.y + 0.35f, data.width*2*((player.health/player.maxHealth)), 0.05f);

    }

    @Override
    public void update(float deltaTime) {
        batch.begin();
        super.update(deltaTime);
        batch.end();
        camera.update();
    }
}
