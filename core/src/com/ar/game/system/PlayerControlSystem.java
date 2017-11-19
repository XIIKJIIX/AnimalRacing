package com.ar.game.system;

import com.ar.game.AnimalRacing;
import com.ar.game.component.*;

import static com.ar.game.component.StateComponent.State.*;
import static com.ar.game.constant.B2Dvars.PPM;

import com.ar.game.constant.SkillMapper;
import com.ar.game.entity.*;
import com.ar.game.handler.KeyboardController;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.google.inject.Inject;

import java.util.HashMap;

public class PlayerControlSystem extends IteratingSystem {
    KeyboardController controller = AnimalRacing.controller;
    private Engine engine;
    private World world;
    private AssetManager manager;
    private HashMap<String, Sound> sound = new HashMap<>();
    @Inject
    public PlayerControlSystem(World world, Engine engine, AssetManager manager) {
        super(Family.all(PlayerComponent.class).get());
        this.world = world;
        this.manager = manager;
        this.engine = engine;
        sound.put("invoke", manager.get("sounds/Invoke.mp3", Sound.class));
        sound.put("heal", manager.get("sounds/heal.mp3", Sound.class));
        sound.put("use_iceball", manager.get("sounds/Crystal_Maiden_preattack2.mp3", Sound.class));
    }
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        StateComponent state = Mapper.state.get(entity);
        PlayerComponent player = Mapper.player.get(entity);
        PhysicsComponent physics = Mapper.physics.get(entity);
        TransformComponent transform = Mapper.transform.get(entity);
        DataComponent data = Mapper.data.get(entity);

        float yVelocity = physics.body.getLinearVelocity().y;
        float xVelocity = physics.body.getLinearVelocity().x;
        Vector2 worldCenter = physics.body.getWorldCenter();
        // If body going down set to falling state
        if (yVelocity > 0) state.set(FALLING);

        // If body stationary on y axis
        if (yVelocity > 0 || (yVelocity < 0) && state.prevState == JUMPING) state.set(JUMPING);
        else if (yVelocity < 0) state.set(FALLING);
        else if (xVelocity != 0) state.set(MOVING);
        else state.set(NORMAL);

        if (controller.KEY_MAP.get(player.leftKey))
            physics.body.setLinearVelocity(
                    MathUtils.lerp(xVelocity, player.spRate*(-5F), 0.2F),
                    yVelocity
                );

        if (controller.KEY_MAP.get(player.rightKey))
            physics.body.setLinearVelocity(
                    MathUtils.lerp(xVelocity, player.spRate*5F, 0.2F),
                    yVelocity
            );

        if (!controller.KEY_MAP.get(player.leftKey) && !controller.KEY_MAP.get(player.rightKey))
            physics.body.setLinearVelocity(
                    MathUtils.lerp(xVelocity, 0F, 0.15F),
                    yVelocity
            );

        if (controller.KEY_MAP.get(player.upKey) && (state.get() == NORMAL || state.get() == MOVING)) {
            physics.body.applyLinearImpulse(0, 4F, worldCenter.x, worldCenter.y, true);
            state.set(JUMPING);
        }

        if (Gdx.input.isKeyJustPressed(player.castKey)) {
            if (player.currSkill != null) {
                String skillOrb = player.currSkill;
                if (player.cooldown.get(skillOrb) > 0) {
                    System.out.println(player.currSkill + " is on cooldown. "+player.cooldown.get(skillOrb));
                } else {
                    float toLeft = 1;
                    Skill skill = SkillMapper.map(player.currSkill);
                    SkillComponent skillComponent = Mapper.skill.get((Entity) skill);
                    if (!state.isRunningRight) {
                        skillComponent.isRight = false;
                        toLeft = -1;
                    }
                    if (skill instanceof IceBall) {
                        skill.setTransform(new Vector2(transform.position.x + toLeft*(30f/PPM), transform.position.y));
                        engine.addEntity((Entity) skill);
                        sound.get("use_iceball").play(0.5f);
                    } else if (skill instanceof Bomb) {
                        skill.setTransform(new Vector2(transform.position.x + toLeft*(40f/PPM), transform.position.y));
                        PhysicsComponent skillPhysics = Mapper.physics.get((Entity) skill);
                        skillPhysics.body.setLinearVelocity(toLeft*5f, 5f);
                        engine.addEntity((Entity) skill);
                    } else if (skill instanceof Heal) {
                        skill.setTransform(new Vector2(transform.position.x, transform.position.y));
                        player.health = Math.min(player.health + skillComponent.type.get(SkillComponent.Type.HEAL), player.maxHealth);
                        sound.get("heal").play(0.5f);
                    } else if (skill instanceof Wall) {
                        skill.setTransform(new Vector2(transform.position.x - 0.4f, transform.position.y + 0.2f));
                        engine.addEntity((Entity) skill);
                    }

                    player.cooldown.put(player.currSkill, skill.getCooldown());
                }
            } else {
                System.out.println("Invoke First");
            }

        }

        if (Gdx.input.isKeyJustPressed(player.addQuarz)) {
            player.orbs.addFirst(PlayerComponent.Orb.QUARZ);
            player.orbs.removeLast();
            System.out.println(player.orbs);
        }

        if (Gdx.input.isKeyJustPressed(player.addExort)) {
            player.orbs.addFirst(PlayerComponent.Orb.EXORT);
            player.orbs.removeLast();
            System.out.println(player.orbs);
        }

        if (Gdx.input.isKeyJustPressed(player.invokeKey)) {
            player.currSkill = player.orbs.toString();
            System.out.println(player.currSkill);
            sound.get("invoke").play(0.4f);
        }

        player.cooldown.replaceAll((k, v) -> Math.max(v - deltaTime, 0));
        player.debufTime = Math.max(player.debufTime-deltaTime, 0);
        if (player.debufTime == 0) player.spRate = 1;
    }
}
