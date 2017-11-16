package com.ar.game.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public interface Skill {
    void setTransform(Vector2 position);
    Body getBody();
    float getCooldown();
}
