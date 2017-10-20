package com.ar.game.entity;

import static com.ar.game.constant.B2Dvars.*;

import com.ar.game.component.*;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Player extends Entity {
    private World world;

    public Player(World world, PlayerComponent player) {
        this.world = world;

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(bodyDef);

        shape.setAsBox(20F / PPM, 20F / PPM);
        fixtureDef.shape = shape;
        fixtureDef.restitution = 0.5F;
        body.createFixture(fixtureDef);
        shape.dispose();


        TransformComponent transformComponent = new TransformComponent(new Vector2(640F / PPM, 520F / PPM));
        body.setTransform(transformComponent.position, 0F);
        // Set user data of body to this entity for handling collide
        body.setUserData(this);

        super.add(transformComponent);
        super.add(new PhysicsComponent(body));
        super.add(player);
        super.add(new StateComponent());
    }
}
