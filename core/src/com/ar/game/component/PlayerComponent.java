package com.ar.game.component;

import com.ar.game.entity.Skill;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class PlayerComponent implements Component {
    public static enum Orb {QUARZ, EXORT};
    public Queue<Orb> orbs = new Queue<>(2);
    public String currSkill;
    public HashMap<String, Float> cooldown = new HashMap<>(4);
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

        Arrays.asList("[QUARZ, QUARZ]", "[QUARZ, EXORT]", "[EXORT, QUARZ]", "[EXORT, EXORT]")
                .forEach(x -> {
                  cooldown.put(x, 0f);
        });
    }
}
