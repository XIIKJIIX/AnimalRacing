package com.ar.game.screen;

import com.ar.game.AnimalRacing;
import com.ar.game.system.Systems;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.google.inject.Inject;

public class EndScreen extends ScreenAdapter {

    private Texture player1win;
    private Texture player2win;
    private Texture homeInactive;
    private Texture homeActive;
    private Texture retryInactive;
    private Texture retryActive;
    private Texture player1;
    private Texture player2;

    private ImageButton homeButtonInactive;
    private ImageButton homeButtonActive;
    private ImageButton retryButtonInactive;
    private ImageButton retryButtonActive;

    private Stage stage;

    private AnimalRacing game;
    private Batch batch;

    @Inject
    public EndScreen (AssetManager manager, SpriteBatch batch, AnimalRacing game){
        this.batch = batch;
        this.game = game;
        player1win = manager.get("endscreen_player1win.png", Texture.class);
        player2win = manager.get("endscreen_player2win.png", Texture.class);
        homeInactive = manager.get("Home_inactive.png", Texture.class);
        homeActive = manager.get("Home_active.png", Texture.class);
        retryInactive = manager.get("Retry_inactive.png", Texture.class);
        retryActive = manager.get("Retry_active.png", Texture.class);
        player1 = manager.get("cat_inactive.png", Texture.class);
        player2 = manager.get("frog_inactive.png", Texture.class);
    }

    @Override
    public void show(){
        stage = new Stage(new ScreenViewport());
        homeButtonInactive = new ImageButton(new TextureRegionDrawable(new TextureRegion(homeInactive)));
        homeButtonActive = new ImageButton(new TextureRegionDrawable(new TextureRegion(homeActive)));
        retryButtonInactive = new ImageButton(new TextureRegionDrawable(new TextureRegion(retryInactive)));
        retryButtonActive = new ImageButton(new TextureRegionDrawable(new TextureRegion(retryActive)));

        homeButtonInactive.setHeight(Gdx.graphics.getHeight()/6);
        homeButtonInactive.setWidth(Gdx.graphics.getHeight()/6);
        homeButtonActive.setHeight(Gdx.graphics.getHeight()/6);
        homeButtonActive.setWidth(Gdx.graphics.getHeight()/6);

        retryButtonInactive.setHeight(Gdx.graphics.getHeight()/6);
        retryButtonInactive.setWidth(Gdx.graphics.getHeight()/6);
        retryButtonActive.setHeight(Gdx.graphics.getHeight()/6);
        retryButtonActive.setWidth(Gdx.graphics.getHeight()/6);

        homeButtonInactive.setPosition(Gdx.graphics.getWidth()/2 - homeButtonInactive.getWidth()/2 - Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/6);
        retryButtonInactive.setPosition(Gdx.graphics.getWidth()/2 - retryButtonInactive.getWidth()/2 + Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/6);
        homeButtonActive.setPosition(Gdx.graphics.getWidth()/2 - homeButtonActive.getWidth()/2 - Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/6);
        retryButtonActive.setPosition(Gdx.graphics.getWidth()/2 - retryButtonActive.getWidth()/2 + Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/6);
        stage.addActor(homeButtonInactive);
        stage.addActor(retryButtonInactive);

        homeButtonInactive.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                stage.addActor(homeButtonActive);
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.dispose();
                game.setScreen(game.injector.getInstance(MainMenuScreen.class));
                return true;
            }
        });

        homeButtonActive.addListener(new ClickListener() {
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
                homeButtonActive.remove();
                stage.addActor(homeButtonInactive);
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.dispose();
                game.setScreen(game.injector.getInstance(MainMenuScreen.class));
                return true;
            }
        });

        retryButtonInactive.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                stage.addActor(retryButtonActive);
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.injector.getInstance(Systems.class).list.stream()
                        .map(systemClass -> game.injector.getInstance(systemClass))
                        .forEach(entitySystem -> game.engine.addSystem(entitySystem));
                game.setScreen(game.injector.getInstance(PlayScreen.class));
                return true;
            }
        });

        retryButtonActive.addListener(new ClickListener() {
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
                retryButtonActive.remove();
                stage.addActor(retryButtonInactive);
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.injector.getInstance(Systems.class).list.stream()
                        .map(systemClass -> game.injector.getInstance(systemClass))
                        .forEach(entitySystem -> game.engine.addSystem(entitySystem));
                game.setScreen(game.injector.getInstance(PlayScreen.class));
                return true;
            }
        });

        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if (true) { //player1 win
            batch.draw(player1win, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            }
        else {
            batch.draw(player2win, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
        batch.draw(player1, Gdx.graphics.getWidth()/2 - player1.getWidth()/2 - Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/2 + player1.getHeight()/2);
        batch.draw(player2, Gdx.graphics.getWidth()/2 - player2.getWidth()/2 - Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/2 - player2.getHeight()/2);
        batch.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
    }
}
