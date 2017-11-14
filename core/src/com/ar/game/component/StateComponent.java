package com.ar.game.component;

import com.badlogic.ashley.core.Component;

public class StateComponent implements Component {
    public static enum State {NORMAL, JUMPING, FALLING, MOVING, HIT};
    public State state = State.NORMAL;
    public State prevState = State.NORMAL;
    public float time = 0.0f;
    public boolean isLooping = false;

    public void set(State newState) {
        state = newState;
        time = 0.0f;
    }

    public State get() {
        return state;
    }
}