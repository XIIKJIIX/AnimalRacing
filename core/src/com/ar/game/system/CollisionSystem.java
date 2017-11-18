package com.ar.game.system;

import com.ar.game.component.*;
import com.ar.game.entity.Bomb;
import com.ar.game.entity.IceBall;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.World;
import com.google.inject.Inject;

public class CollisionSystem extends IteratingSystem {
    private World world;
    private Engine engine;
    @Inject
    public CollisionSystem(Engine engine, World world) {
        super(Family.all(CollisionComponent.class).get());
        this.engine = engine;
        this.world = world;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        // Get player collision component
        CollisionComponent collision = Mapper.collision.get(entity);
        DataComponent data = Mapper.data.get(entity);
        Entity collidedEntity = collision.getCollidedEntity();

        if (collidedEntity != null) {
            DataComponent colData = Mapper.data.get(collidedEntity);
            TypeComponent colType = Mapper.type.get(collidedEntity);

            if (colType != null) {
                short group = colType.getGroup();
                SkillComponent skill = Mapper.skill.get(entity);
                if (skill != null) {
                    switch (group) {
                        case TypeComponent.PLAYER:
                            if (skill.shotType == SkillComponent.ShotType.OBJECT) break;
                            PlayerComponent player = Mapper.player.get(collidedEntity);
                            PhysicsComponent physics = Mapper.physics.get(collidedEntity);
                            processSkill(player, skill, physics);
                        case TypeComponent.SCENERY:
                            StateComponent skillState = Mapper.state.get(entity);
                            PhysicsComponent skillPhysics = Mapper.physics.get(entity);
                            if (entity instanceof Bomb) {
                                world.destroyBody(skillPhysics.body);
                                skillState.set(StateComponent.State.HIT);
                            }
                            if (skillState.state == StateComponent.State.HIT) break;
                            SystemHelper.removeEntity(entity, world, engine);
                            break;
                        default:
                            System.out.println("hit nope");
                    }
                }
                collision.setCollidedEntity(null);
            }
        }
    }

    private void processSkill(PlayerComponent player, SkillComponent skill, PhysicsComponent physics) {
        skill.type.forEach((k, v) -> {
            switch (k) {
                case HEAL:
                    player.health = Math.min(player.maxHealth, player.health+v);
                    break;
                case SLOW:
                    player.spRate -= v;
                    player.debufTime = 3;
                    break;
                case DAMAGE:
                    player.health = Math.max(0, player.health-v);
                    break;
                default:
                    System.out.println("Skill did nothing");
            }
        });
    }
}
