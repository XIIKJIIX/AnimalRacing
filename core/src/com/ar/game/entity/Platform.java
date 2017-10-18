package com.ar.game.entity;

import com.ar.game.AnimalRacing;
import com.ar.game.component.Mapper;
import com.ar.game.component.PhysicsComponent;
import com.ar.game.component.TextureRegionComponent;
import com.ar.game.component.TransformComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.google.inject.Inject;

public class Platform extends Entity {
    private World world;

    @Inject
    public Platform(World world) {
        this.world = world;
        PolygonShape shape = new PolygonShape();
        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = this.world.createBody(bodyDef);

        shape.setAsBox(5F, 0.5F);
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        super.add(new TransformComponent(new Vector2(15F, 12.5F)));

        TransformComponent transformComponent = Mapper.transform.get(this);
        body.setTransform(transformComponent.position, 0F);

        super.add(new PhysicsComponent(body));
        shape.dispose();
    }
}
