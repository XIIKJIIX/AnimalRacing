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
import com.badlogic.gdx.graphics.Texture;
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

import static com.ar.game.MapBuilder.buildShapes;
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
    private Texture bg = new Texture("game_background.png");

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

    private AnimationComponent getAnimate(String name) {
        System.out.println(name);
        TextureAtlas atlas = new TextureAtlas(name+"run.txt");
        Array<TextureRegion> frames = new Array<>();
        for (int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(atlas.findRegion(name+"run"+i), i, 0, 354, 656));
        }
        Animation player1Run = new Animation(0.1f, frames);
        frames.clear();
        frames.add(new TextureRegion(atlas.findRegion(name+"run0"), 0, 0, 354, 656));
        Animation player1Normal = new Animation(0.1f, frames);
        frames.clear();
        atlas = new TextureAtlas(name+"run.txt");
        for (int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(atlas.findRegion(name+"run"+i), i, 0, 354, 656));
        }
        Animation player1Jump = new Animation(0.1f, frames);
        frames.clear();
        HashMap<StateComponent.State, Animation> stateAnimation = new HashMap<>();
        stateAnimation.put(NORMAL, player1Normal);
        stateAnimation.put(JUMPING, player1Jump);
        stateAnimation.put(MOVING, player1Run);
        return new AnimationComponent(stateAnimation);
    }

    private void createEntities() {
        Entity player1 = new Player(
                world,
                new PlayerComponent(LEFT, RIGHT, UP, SHIFT_RIGHT, 300F, ENTER, PERIOD, SLASH),
                new TypeComponent(TypeComponent.PLAYER),
                new DataComponent(30F / PPM, 12F / PPM, "Player1")
        );
        player1.add(getAnimate(SelectCharacterScreenP1.characterP1));
        engine.addEntity(player1);
        Entity player2 = new Player(
                world,
                new PlayerComponent(A, D, W, Q, 300F, E, Z, X),
                new TypeComponent(TypeComponent.PLAYER),
                new DataComponent(30F / PPM, 12F / PPM, "Player2")
        );
        player2.add(getAnimate(SelectCharacterScreenP2.characterP2));
        engine.addEntity(player2);

    }

    private void createMap() {
        tiledMap = new TmxMapLoader().load("map.tmx");
        tmr = new OrthogonalTiledMapRenderer(tiledMap, (float) 1 / PPM);
        MapBuilder.buildShapes(tiledMap, PPM , world, "ground", engine);
        MapBuilder.buildShapes(tiledMap, PPM, world, "box", engine);
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
        batch.begin();
        batch.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
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
