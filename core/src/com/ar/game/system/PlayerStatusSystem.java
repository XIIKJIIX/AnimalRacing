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
    private Texture player1Skill, player2Skill;
    private AssetManager manager;
    private HashMap<String, Texture> textureSkillMapper = new HashMap<>();

    @Inject
    public PlayerStatusSystem(OrthographicCamera camera, SpriteBatch batch, AssetManager manager) {
        super(Family.all(PlayerComponent.class).get());
        this.camera = camera;
        this.batch = batch;
        this.manager = manager;
        textureSkillMapper.put("[QUARZ, QUARZ]", manager.get("skills/iceball.png", Texture.class));
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PlayerComponent player = Mapper.player.get(entity);
        DataComponent data = Mapper.data.get(entity);
        if (data.name.equals("Player1")) {
            if (player.currSkill != null) {
                System.out.println("player1 draw");
                player1Skill = textureSkillMapper.get(player.currSkill);
                batch.draw(player1Skill, camera.position.x, camera.position.y, 0.5f, 0.5f);
            }
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
