package com.ar.game.handler;

import com.ar.game.AnimalRacing;
import com.ar.game.component.Mapper;
import com.ar.game.component.PhysicsComponent;
import com.ar.game.component.TextureRegionComponent;
import com.ar.game.component.TransformComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.google.inject.Inject;

public class MyInputProcessor extends InputAdapter {
    private World world;
    private OrthographicCamera camera;
    private Engine engine;

    @Inject
    public MyInputProcessor(World world, OrthographicCamera camera, Engine engine) {
        this.camera = camera;
        this.engine = engine;
        this.world = world;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Gdx.app.log("Mylog",""+screenX+" "+screenY);
        // Change from screen pos to box2d pos
        Vector3 worldPosition = camera.unproject(new Vector3((float) screenX, (float) screenY, 0F));
        Entity entity = new Entity();
        entity.add(new TextureRegionComponent(new TextureRegion(AnimalRacing.img)));
        entity.add(new TransformComponent(new Vector2(worldPosition.x, worldPosition.y), 0F, 0.25F));
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(32F, 32F);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1F;
        body.createFixture(fixtureDef);
        body.setTransform(Mapper.transform.get(entity).position, 0F);
        entity.add(new PhysicsComponent(body));
        engine.addEntity(entity);
        shape.dispose();

        return true;
    }
}
