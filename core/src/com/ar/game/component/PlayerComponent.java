package com.ar.game.component;

import com.ar.game.entity.Skill;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Queue;

import java.util.HashMap;


public class PlayerComponent implements Component {
    public static enum Orb {QUARZ, EXORT};
    public Queue<Orb> orbs = new Queue<>(2);
    public Skill currSkill;
    public HashMap<Orb, Float> cooldown = new HashMap<>(10);
    public int leftKey, rightKey, upKey, castKey, invokeKey, addQuarz, addExort;
    public float health;
    public float maxHealth;

    public PlayerComponent(int leftKey,
                           int rightKey,
                           int upKey,
                           int castKey,
                           float maxHealth,
                           int invokeKey,
                           int addQuarz,
                           int addExort) {
        // For Mock up only
        orbs.addFirst(Orb.QUARZ);
        orbs.addFirst(Orb.QUARZ);
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.upKey = upKey;
        this.castKey = castKey;
        this.health = maxHealth;
        this.maxHealth = maxHealth;
        this.invokeKey = invokeKey;
        this.addExort = addExort;
        this.addQuarz = addQuarz;
    }
}
