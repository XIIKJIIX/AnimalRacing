package com.ar.game.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class TransformComponent implements Component {
    public Vector2 position;
    public Float angelRadiant;
    public Float scale;

    public TransformComponent(Vector2 position, Float angelRadiant, Float scale) {
        this.position = position;
        this.scale = scale;
        this.angelRadiant = angelRadiant;
    }
}
