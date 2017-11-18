package com.ar.game.constant;

import com.ar.game.AnimalRacing;
import com.ar.game.component.PlayerComponent;
import com.ar.game.component.PlayerComponent.Orb;
import com.ar.game.entity.Bomb;
import com.ar.game.entity.IceBall;
import com.ar.game.entity.Skill;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Queue;

public class SkillMapper {
    public static Skill map(String orbs){
        World world = AnimalRacing.injector.getInstance(World.class);
        Skill skill;
        switch (orbs) {
            case "[QUARZ, QUARZ]":
                skill = new IceBall(world);
                break;
            case "[EXORT, EXORT]":
                skill = new Bomb(world);
                break;
            default:
                skill = new IceBall(world);
        }
        return skill;
    }
}
