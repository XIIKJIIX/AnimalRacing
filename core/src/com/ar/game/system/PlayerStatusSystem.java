package com.ar.game.system;

import com.ar.game.component.DataComponent;
import com.ar.game.component.Mapper;
import com.ar.game.component.PlayerComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.inject.Inject;

import java.util.HashMap;

public class PlayerStatusSystem extends IteratingSystem{
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture playerSkill;
    private AssetManager manager;
    HashMap<PlayerComponent.Orb, Texture> orbMap = new HashMap<>();
    private HashMap<String, Texture> textureSkillMapper = new HashMap<>();

    @Inject
    public PlayerStatusSystem(OrthographicCamera camera, SpriteBatch batch, AssetManager manager) {
        super(Family.all(PlayerComponent.class, DataComponent.class).get());
        this.camera = camera;
        this.batch = batch;
        this.manager = manager;
        textureSkillMapper.put("[QUARZ, QUARZ]", manager.get("skills/iceball.png", Texture.class));
        textureSkillMapper.put("[EXORT, EXORT]", manager.get("skills/bomb.png", Texture.class));
        textureSkillMapper.put("[EXORT, QUARZ]", manager.get("skills/potion.png", Texture.class));
        textureSkillMapper.put("[QUARZ, EXORT]", manager.get("skills/treetop.png", Texture.class));
        orbMap.put(PlayerComponent.Orb.QUARZ, manager.get("skills/quarz.png", Texture.class));
        orbMap.put(PlayerComponent.Orb.EXORT, manager.get("skills/exort.png", Texture.class));
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PlayerComponent player = Mapper.player.get(entity);
        DataComponent data = Mapper.data.get(entity);
        if (player.currSkill != null) {
            playerSkill = textureSkillMapper.get(player.currSkill);
            if (data.name.equals("Player2")) {
                batch.draw( //Draw Skill
                        playerSkill,
                        camera.position.x - camera.viewportWidth / 2,
                        camera.position.y + camera.viewportHeight / 3,
                        0.5f,
                        0.5f
                );
            } else {
                batch.draw(
                        playerSkill,
                        camera.position.x + camera.viewportWidth / 3 + 0.55f,
                        camera.position.y + camera.viewportHeight / 3,
                        0.5f,
                        0.5f
                );
            }
        }
        if (data.name.equals("Player2")) {
            batch.draw( //Draw Orb1
                    orbMap.get(player.orbs.get(0)),
                    camera.position.x - camera.viewportWidth / 2 + 0.5f,
                    camera.position.y + camera.viewportHeight / 3,
                    0.5f,
                    0.5f
            );
            batch.draw( //Draw Orb2
                    orbMap.get(player.orbs.get(1)),
                    camera.position.x - camera.viewportWidth / 2 + 1f,
                    camera.position.y + camera.viewportHeight / 3,
                    0.5f,
                    0.5f
            );
        } else {
            batch.draw(
                    orbMap.get(player.orbs.get(0)),
                    camera.position.x + camera.viewportWidth / 3 + 0.05f,
                    camera.position.y + camera.viewportHeight / 3,
                    0.5f,
                    0.5f
            );
            batch.draw(
                    orbMap.get(player.orbs.get(1)),
                    camera.position.x + camera.viewportWidth / 3 - 0.45f,
                    camera.position.y + camera.viewportHeight / 3,
                    0.5f,
                    0.5f
            );
        }

    }

    @Override
    public void update(float deltaTime) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        super.update(deltaTime);
        batch.end();
        camera.update();
    }
}
