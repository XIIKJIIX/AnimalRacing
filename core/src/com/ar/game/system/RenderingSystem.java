package com.ar.game.system;

import com.ar.game.component.Mapper;
import com.ar.game.component.TextureComponent;
import com.ar.game.component.TextureRegionComponent;
import com.ar.game.component.TransformComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.google.inject.Inject;

public class RenderingSystem extends IteratingSystem {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tmr;

    @Inject
    public RenderingSystem(SpriteBatch batch, OrthographicCamera camera) {
        super(Family.all(TransformComponent.class)
                .one(TextureComponent.class, TextureRegionComponent.class)
                .get()
        );
        this.batch = batch;
        this.camera = camera;
        tiledMap = new TmxMapLoader().load("try.tmx");
        tmr = new OrthogonalTiledMapRenderer(tiledMap, 0.05f);
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
        tmr.setView(camera);
        tmr.render();
        batch.begin();
        super.update(deltaTime);
        batch.end();
        if (Gdx.input.isKeyPressed(Input.Keys.F)){
            camera.position.y +=1f;
            System.out.println("Y: "+camera.position.y);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.V)){
            camera.position.y -=1f;
            System.out.println("Y: "+camera.position.y);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.C)){
            camera.position.x -=1f;
            System.out.println("X: "+camera.position.x);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.B)){
            camera.position.x +=1f;
            System.out.println("X: "+camera.position.x);
        }
        camera.update();
    }
}
