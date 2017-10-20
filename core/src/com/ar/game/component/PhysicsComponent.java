package com.ar.game.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;

public class PhysicsComponent implements Component {
    public Body body;
    public String name;

    public PhysicsComponent(Body body, String name) {
        this.body = body;
        this.name = name;
    }
}
