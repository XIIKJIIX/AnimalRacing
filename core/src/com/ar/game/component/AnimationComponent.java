package com.ar.game.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.HashMap;

public class AnimationComponent implements Component {
    public HashMap<StateComponent.State, Animation> animation;

    public AnimationComponent(HashMap<StateComponent.State, Animation> animation) {
        this.animation = animation;
    }
}
