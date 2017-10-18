package com.ar.game.entity;

import com.ar.game.component.Mapper;
import com.ar.game.component.PhysicsComponent;
import com.ar.game.component.TransformComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.google.inject.Inject;

public class Player extends Entity {
    private World world;

    @Inject
    public Player(World world) {
        this.world = world;

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(bodyDef);

        shape.setAsBox(0.5F, 0.5F);
        fixtureDef.shape = shape;
        fixtureDef.restitution = 0.5F;
        body.createFixture(fixtureDef);
        shape.dispose();

        super.add(new TransformComponent(new Vector2(15F, 18F)));
        TransformComponent transformComponent = Mapper.transform.get(this);
        body.setTransform(transformComponent.position, 0F);

        super.add(new PhysicsComponent(body));
    }
}
