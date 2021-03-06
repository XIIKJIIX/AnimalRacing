package com.ar.game.entity;

import com.ar.game.component.*;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static com.ar.game.constant.B2Dvars.PPM;

public class Player extends Entity {

    public Player(World world,
                  PlayerComponent player,
                  TypeComponent type,
                  DataComponent data) {

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(bodyDef);

        shape.setAsBox(data.width, data.height);
        fixtureDef.shape = shape;
        fixtureDef.restitution = 0.5F;
        fixtureDef.filter.groupIndex = type.getGroup();
        body.createFixture(fixtureDef);
        shape.dispose();


        TransformComponent transformComponent = new TransformComponent(new Vector2(80F / PPM, 100F / PPM));
        body.setTransform(transformComponent.position, 0F);
        // Set user data of body to this entity for handling collide
        body.setUserData(this);

        super.add(transformComponent);
        super.add(new PhysicsComponent(body));
        super.add(player);
        super.add(type);
        super.add(data);
        super.add(new CollisionComponent());
        super.add(new StateComponent());
    }
}
