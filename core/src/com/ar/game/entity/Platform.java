package com.ar.game.entity;

import static com.ar.game.constant.B2Dvars.*;
import com.ar.game.AnimalRacing;
import com.ar.game.component.Mapper;
import com.ar.game.component.PhysicsComponent;
import com.ar.game.component.TransformComponent;
import com.badlogic.ashley.core.Entity;
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

        shape.setAsBox(110F / PPM, 10F / PPM);
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        super.add(new TransformComponent(new Vector2(640F / PPM, 360F / PPM)));

        TransformComponent transformComponent = Mapper.transform.get(this);
        body.setTransform(transformComponent.position, 0F);

        super.add(new PhysicsComponent(body));
        shape.dispose();
    }
}
