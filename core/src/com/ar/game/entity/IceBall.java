package com.ar.game.entity;

import com.ar.game.component.*;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.google.inject.Inject;

import java.util.HashMap;

import static com.ar.game.constant.B2Dvars.PPM;


public class IceBall extends Entity implements Skill {

    public TransformComponent transform;
    public PhysicsComponent physics;
    public DataComponent data;
    public SkillComponent skill;
    public TypeComponent type;
    public StateComponent state;
    public AnimationComponent animationComponent;
    public Body body;

    @Inject
    public IceBall(World world) {
        skill = new SkillComponent(SkillComponent.ShotType.BULLET, SkillComponent.ON_HIT.GONE, 10f);
        skill.addSkill(SkillComponent.Type.DAMAGE, 15f);
        skill.addSkill(SkillComponent.Type.SLOW, 35f);
        add(skill);

        data = new DataComponent(5f / PPM, 5f / PPM, "ICE_BALL");
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

        TextureAtlas atlas = new TextureAtlas("skills/ice_ball.txt");
        Array<TextureRegion> frames = new Array<>();
        for (int i = 0; i < 6; i++) {
            frames.add(new TextureRegion(atlas.findRegion((i+1)+""), 0, 0, 512, 197));
        }
        Animation animation = new Animation(0.1f, frames);
        frames.clear();
        HashMap<StateComponent.State, Animation> stateAnimation = new HashMap<>();
        stateAnimation.put(StateComponent.State.MOVING, animation);
        animationComponent = new AnimationComponent(stateAnimation);
        add(animationComponent);

        state = new StateComponent();
        state.set(StateComponent.State.MOVING);
        add(state);
    }

    public void setTransform(Vector2 position) {
        transform = new TransformComponent(position);
        body.setTransform(transform.position, 0);
        physics = new PhysicsComponent(body);
        add(physics);
        add(transform);
    }
}
