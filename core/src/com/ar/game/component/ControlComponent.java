package com.ar.game.component;

import com.badlogic.ashley.core.Component;


public class ControlComponent implements Component {
    private int jump;

    public ControlComponent(int jump) {
        this.jump = jump;
    }
}
