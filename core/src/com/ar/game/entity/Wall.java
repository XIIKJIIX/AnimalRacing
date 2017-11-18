package com.ar.game.entity;

import com.ar.game.component.*;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

import static com.ar.game.constant.B2Dvars.PPM;

public class Wall extends Entity implements Skill {
    public TransformComponent transform;
    public PhysicsComponent physics;
    public DataComponent data;
    public SkillComponent skill;
    public TypeComponent type;
    public StateComponent state;
    public AnimationComponent animationComponent;
    public Body body;
    public Wall(World world) {
        skill = new SkillComponent(SkillComponent.ShotType.OBJECT, SkillComponent.ON_HIT.GONE, 12f, 5f, 0.25f);
        skill.addSkill(SkillComponent.Type.DAMAGE, 100f);
        add(skill);

        data = new DataComponent(35f / PPM, 20 / PPM, "WALL");
        add(data);

        type = new TypeComponent(TypeComponent.SKILL);
        add(type);

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        bodyDef.type = BodyDef.BodyType.StaticBody  ;
        body = world.createBody(bodyDef);
        shape.setAsBox(data.width, data.height);
        fixtureDef.shape = shape;
        fixtureDef.filter.groupIndex = type.getGroup();
        body.createFixture(fixtureDef);
        body.setUserData(this);

        shape.dispose();
        state = new StateComponent();
        state.set(StateComponent.State.NORMAL);
        add(state);

        add(new CollisionComponent());

        Array<TextureRegion> frames = new Array<>();
        HashMap<StateComponent.State, Animation> stateAnimation = new HashMap<>();
        Texture texture = new Texture("skills/treetop.png");
        TextureRegion region = new TextureRegion(texture);
        frames.add(region);
        Animation animation = new Animation(0.1f, frames);
        frames.clear();
        stateAnimation.put(StateComponent.State.NORMAL, animation);
        add(new AnimationComponent(stateAnimation));

        state = new StateComponent();
        state.set(StateComponent.State.NORMAL);
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
        return null;
    }

    @Override
    public float getCooldown() {
        return skill.cooldown;
    }
}
