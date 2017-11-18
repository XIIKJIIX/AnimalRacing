package com.ar.game.entity;

import com.ar.game.component.*;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static com.ar.game.constant.B2Dvars.PPM;

public class Bomb extends Entity implements Skill {
    public TransformComponent transform;
    public PhysicsComponent physics;
    public DataComponent data;
    public SkillComponent skill;
    public TypeComponent type;
    public StateComponent state;
    public AnimationComponent animationComponent;
    public Body body;

    public Bomb(World world) {
        skill = new SkillComponent(SkillComponent.ShotType.PROJECTILE, SkillComponent.ON_HIT.GONE, 12f, 5f, 0.25f);
        skill.addSkill(SkillComponent.Type.DAMAGE, 100f);
        add(skill);

        data = new DataComponent(15f / PPM, 15f / PPM, "BOMB");
        add(data);

        type = new TypeComponent(TypeComponent.SKILL);
        add(type);

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);
        shape.setRadius(data.height);
        fixtureDef.shape = shape;
        fixtureDef.filter.groupIndex = type.getGroup();
        body.createFixture(fixtureDef);
        shape.dispose();
        body.setUserData(this);

        add(new CollisionComponent());

        state = new StateComponent();
        state.set(StateComponent.State.MOVING);
        add(state);
    }

    @Override
    public void setTransform(Vector2 position) {
        transform = new TransformComponent(position);
        body.setTransform(transform.position, 0);
        physics = new PhysicsComponent(body);
        add(physics);
        add(transform);
    }

    @Override
    public Body getBody() {
        return physics.body;
    }

    @Override
    public float getCooldown() {
        return skill.cooldown;
    }
}
