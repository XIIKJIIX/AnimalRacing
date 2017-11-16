package com.ar.game.system;

import com.ar.game.component.Mapper;
import com.ar.game.component.StateComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.google.inject.Inject;

public class StateUpdateSystem extends IteratingSystem {

    @Inject
    public StateUpdateSystem() {
        super(Family.all(StateComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        StateComponent state = Mapper.state.get(entity);
        state.stateTimer = state.state == state.prevState ? state.stateTimer + deltaTime: 0;
        state.prevState = state.state;
    }
}
