package com.ar.game.system;

import com.ar.game.component.Mapper;
import com.ar.game.component.TextureComponent;
import com.ar.game.component.TextureRegionComponent;
import com.ar.game.component.TransformComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.google.inject.Inject;

public class RenderingSystem extends IteratingSystem {
    private SpriteBatch batch;
    private OrthographicCamera camera;

    @Inject
    public RenderingSystem(SpriteBatch batch, OrthographicCamera camera) {
        super(Family.all(TransformComponent.class)
                .one(TextureComponent.class, TextureRegionComponent.class)
                .get()
        );
        this.batch = batch;
        this.camera = camera;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transformComponent = Mapper.transform.get(entity);
        Vector2 position = transformComponent.position;

        TextureComponent textureComponent = Mapper.texture.get(entity);
        if (textureComponent != null) {
            Texture img = textureComponent.texture;
            int imgWidth = img.getWidth();
            int imgHeight = img.getHeight();
            batch.draw(
                    img,
                    position.x - imgWidth / 32F / 2F,
                    position.y - imgHeight / 32F / 2F,
                    imgWidth / 32F,
                    imgHeight / 32F
            );
        }
        TextureRegionComponent textureRegionComponent = Mapper.textureRegion.get(entity);
        if (textureRegionComponent != null) {
            TextureRegion img = textureRegionComponent.textureRegion;
            float scale = transformComponent.scale;
            int imgWidth = img.getRegionWidth();
            int imgHeight = img.getRegionHeight();

            batch.draw(
                    img,
                    position.x - imgWidth / 2,
                    position.y - imgHeight / 2,
                    imgWidth / 2F,
                    imgHeight / 2F,
                    imgWidth,
                    imgHeight,
                    scale,
                    scale,
                    transformComponent.angelRadiant * MathUtils.radiansToDegrees
            );
        }
    }

    @Override
    public void update(float deltaTime) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        super.update(deltaTime);
        batch.end();
        if (Gdx.input.isTouched()) {
            camera.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
            System.out.println("X: "+camera.position.x);
            System.out.println("Y: "+camera.position.y);
        }
        camera.update();
    }
}
