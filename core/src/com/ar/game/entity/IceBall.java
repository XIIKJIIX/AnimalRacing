package com.ar.game.entity;

import com.ar.game.component.*;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.*;

import static com.ar.game.constant.B2Dvars.PPM;


public class IceBall extends Entity {

    public IceBall(World world, TransformComponent transform) {
        SkillComponent skill;
        skill = new SkillComponent(SkillComponent.ShotType.BULLET, SkillComponent.ON_HIT.GONE);
        skill.addSkill(SkillComponent.Type.DAMAGE, 15f);
        skill.addSkill(SkillComponent.Type.SLOW, 35f);
        add(skill);

        DataComponent data = new DataComponent(5f / PPM, 5f / PPM, "ICE_BALL");
        add(data);

        TypeComponent type = new TypeComponent(TypeComponent.SKILL);
        add(type);

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(bodyDef);
        shape.setRadius(data.height);
        fixtureDef.shape = shape;
        fixtureDef.filter.groupIndex = type.getGroup();
        body.createFixture(fixtureDef);
        shape.dispose();
        body.setUserData(this);
        body.setTransform(transform.position, 0);
        PhysicsComponent physics = new PhysicsComponent(body);
        add(physics);

        add(new CollisionComponent());
    }
}
