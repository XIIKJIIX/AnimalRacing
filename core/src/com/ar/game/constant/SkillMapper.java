package com.ar.game.constant;

import com.ar.game.component.PlayerComponent.Orb;
import com.ar.game.entity.IceBall;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Queue;

import java.util.HashMap;

public class SkillMapper {
    public static HashMap<Queue<Orb>, Class<? extends Entity>> SM = new HashMap<>();
    static {
        Queue<Orb> queue = new Queue<>(3);

        queue.addFirst(Orb.QUARZ);
        queue.addFirst(Orb.QUARZ);
        queue.addFirst(Orb.QUARZ);
        SM.put(queue, IceBall.class);
        queue.clear();
    }
}
