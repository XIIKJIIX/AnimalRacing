package com.ar.game.screen;

import com.ar.game.AnimalRacing;
import com.ar.game.MapBuilder;
import com.ar.game.component.DataComponent;
import com.ar.game.component.PlayerComponent;
import com.ar.game.component.TypeComponent;
import com.ar.game.entity.Platform;
import com.ar.game.entity.Player;
import com.ar.game.handler.KeyboardController;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.google.inject.Inject;

import static com.ar.game.constant.B2Dvars.PPM;
import static com.badlogic.gdx.Input.Keys.*;

public class MainGameScreen extends ScreenAdapter {

    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tmr;
    private World world;
    private OrthographicCamera camera;
    private Engine engine;
    private SpriteBatch batch;
    private KeyboardController controller = AnimalRacing.controller;

    @Inject
    public MainGameScreen(World world,
                          OrthographicCamera camera,
                          Engine engine,
                          SpriteBatch batch) {
        this.world = world;
        this.camera = camera;
        this.engine = engine;
        this.batch = batch;
    }

    private void createEntities() {
        engine.addEntity(new Platform(world));
        engine.addEntity(new Player(
                world,
                new PlayerComponent(LEFT, RIGHT, UP, SHIFT_RIGHT, 300F),
                new TypeComponent(TypeComponent.PLAYER),
                new DataComponent(20F / PPM, 20F / PPM, "Player1")
        ));
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
