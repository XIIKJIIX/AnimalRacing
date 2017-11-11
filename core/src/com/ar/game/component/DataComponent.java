package com.ar.game.component;

import com.badlogic.ashley.core.Component;

public class DataComponent implements Component {
    public float height;
    public float width;
    public String name;

    public DataComponent(float height, float width, String name) {
        this.height = height;
        this.width = width;
        this.name = name;
    }
}
