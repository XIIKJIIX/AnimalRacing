package com.ar.game.entity;


import com.ar.game.component.*;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import static com.ar.game.constant.B2Dvars.PPM;

public class Heal extends Entity implements Skill {
    public TransformComponent transform;
    public DataComponent data;
    public SkillComponent skill;
    public TypeComponent type;
    public StateComponent state;
    public Body body;

    public Heal() {
        skill = new SkillComponent(SkillComponent.ShotType.PROJECTILE, SkillComponent.ON_HIT.GONE, 12f, 5f, 0.25f);
        skill.addSkill(SkillComponent.Type.HEAL, 75);
        add(skill);

        data = new DataComponent(15f / PPM, 15f / PPM, "HEAL");
        add(data);
    }

    @Override
    public void setTransform(Vector2 position) {
        transform = new TransformComponent(position);
        add(transform);
    }

    @Override
    public Body getBody() {
        return null;
    }

    @Override
    public float getCooldown() {
        return 0;
    }
}
