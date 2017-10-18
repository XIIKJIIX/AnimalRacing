package com.ar.game.system;

import com.badlogic.ashley.core.EntitySystem;

import java.util.List;

public class Systems {
    public List<Class<? extends EntitySystem>> list;

    public Systems(List<Class<? extends EntitySystem>> list) {
        this.list = list;
    }
}
