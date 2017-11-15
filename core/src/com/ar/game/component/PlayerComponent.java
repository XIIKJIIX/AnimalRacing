package com.ar.game.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Queue;


public class PlayerComponent implements Component {
    public static enum Orb {QUARZ, WEX, EXORT};
    public Queue<Orb> orbs = new Queue<>(3);
    public int leftKey, rightKey, upKey, castKey;
    public float health;
    public float maxHealth;

    public PlayerComponent(int leftKey, int rightKey, int upKey, int castKey, float maxHealth) {
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.upKey = upKey;
        this.castKey = castKey;
        this.health = maxHealth;
        this.maxHealth = maxHealth;
    }
}
