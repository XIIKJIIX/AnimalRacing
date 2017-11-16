package com.ar.game.system;

import com.ar.game.component.DataComponent;
import com.ar.game.component.Mapper;
import com.ar.game.component.PhysicsComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.World;

public class SystemHelper {
    public static void removeEntity(Entity entity, World world, Engine engine) {
        DataComponent data = Mapper.data.get(entity);
        System.out.println("clear "+ data.name);
        PhysicsComponent physics = Mapper.physics.get(entity);
        world.destroyBody(physics.body);
        entity.removeAll();
        engine.removeEntity(entity);
    }
}
