package com.ar.game.entity;

import static com.ar.game.constant.B2Dvars.*;
import com.ar.game.AnimalRacing;
import com.ar.game.component.*;
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
        fixtureDef.filter.groupIndex = TypeComponent.SCENERY;
        body.createFixture(fixtureDef);

        TransformComponent transform = new TransformComponent(new Vector2(640F / PPM, 360F / PPM));
        body.setTransform(transform.position, 0F);
        body.setUserData(this);

        super.add(transform);
        super.add(new PhysicsComponent(body, "Platform"));
        super.add(new CollisionComponent());
        super.add(new TypeComponent(TypeComponent.SCENERY));
        shape.dispose();
    }
}
