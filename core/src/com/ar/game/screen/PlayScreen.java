package com.ar.game.screen;

import com.ar.game.AnimalRacing;
import com.ar.game.MapBuilder;
import com.ar.game.component.*;
import com.ar.game.entity.Player;
import com.ar.game.handler.KeyboardController;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.google.inject.Inject;

import java.util.HashMap;

import static com.ar.game.constant.B2Dvars.PPM;
import static com.badlogic.gdx.Input.Keys.*;
import static com.ar.game.component.StateComponent.State.*;

public class PlayScreen extends ScreenAdapter {

    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tmr;
    private World world;
    private OrthographicCamera camera;
    private Engine engine;
    private SpriteBatch batch;
    private KeyboardController controller = AnimalRacing.controller;

    @Inject
    public PlayScreen(World world,
                      OrthographicCamera camera,
                      Engine engine,
                      SpriteBatch batch) {
        this.world = world;
        this.camera = camera;
        this.engine = engine;
        this.batch = batch;
    }

    private void createEntities() {
        Entity player1 = new Player(
                world,
                new PlayerComponent(LEFT, RIGHT, UP, SHIFT_RIGHT, 300F),
                new TypeComponent(TypeComponent.PLAYER),
                new DataComponent(20F / PPM, 20F / PPM, "Player1")
        );
        TextureAtlas atlas = new TextureAtlas("bear_all.txt");
        Array<TextureRegion> frames = new Array<>();
        for (int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(atlas.findRegion("bear_run"), i * 160, 0, 160, 273));
        }
        Animation player1Run = new Animation(0.1f, frames);
        frames.clear();
        for (int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(atlas.findRegion("bear_jump"), i * 160, 0, 160, 273));
        }
        Animation player1Jump = new Animation(0.1f, frames);
        frames.clear();
        frames.add(new TextureRegion(atlas.findRegion("bear_run"), 0, 0, 160, 273));
        Animation player1Normal = new Animation(0.1f, frames);
        HashMap<StateComponent.State, Animation> stateAnimation = new HashMap<>();
        stateAnimation.put(NORMAL, player1Normal);
        stateAnimation.put(JUMPING, player1Jump);
        stateAnimation.put(MOVING, player1Run);
        player1.add(new AnimationComponent(stateAnimation));
        frames.clear();
        engine.addEntity(player1);
        engine.addEntity(new Player(
                world,
                new PlayerComponent(A, D, W, Q, 300F),
                new TypeComponent(TypeComponent.PLAYER),
                new DataComponent(20F / PPM, 20F / PPM, "Player2")
        ));
    }

    private void createMap() {
        tiledMap = new TmxMapLoader().load("map.tmx");
        tmr = new OrthogonalTiledMapRenderer(tiledMap, (float) 1 / PPM);
        MapBuilder.buildShapes(tiledMap, PPM, world, "ground");
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(controller);
        createMap();
        createEntities();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        engine.update(Gdx.graphics.getDeltaTime());
        tmr.setView(camera);
        tmr.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        tiledMap.dispose();
    }
}
