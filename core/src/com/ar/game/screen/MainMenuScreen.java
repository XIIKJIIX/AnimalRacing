package com.ar.game.screen;

import com.ar.game.AnimalRacing;
import com.ar.game.system.Systems;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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
import javax.swing.*;
import java.awt.*;

public class MainMenuScreen extends ScreenAdapter {

    private Texture exitButtonActiveImage;
    private Texture exitButtonInactiveImage;
    private Texture playButtonActiveImage;
    private Texture playButtonInactiveImage;
    private ImageButton playButtonActive;
    private ImageButton playButtonInactive;
    private ImageButton exitButtonActive;
    private ImageButton exitButtonInactive;

    private SpriteBatch batch;
    private AnimalRacing game;

    Object[] options = {"Yes", "No"};

    private Stage stage;

    @Inject
    public MainMenuScreen (AssetManager manager, SpriteBatch batch, AnimalRacing game){
        this.batch = batch;
        this.game = game;
        playButtonActiveImage = manager.get("Play_active.jpg", Texture.class);
        playButtonInactiveImage = manager.get("Play_inactive.jpg", Texture.class);
        exitButtonActiveImage = manager.get("Exit_active.jpg", Texture.class);
        exitButtonInactiveImage = manager.get("Exit_inactive.jpg", Texture.class);
    }



    @Override
    public void show(){

        playButtonInactive = new ImageButton(new TextureRegionDrawable(new TextureRegion(playButtonInactiveImage)));
        playButtonInactive.setHeight(playButtonInactive.getHeight()/2);
        playButtonInactive.setWidth(playButtonInactive.getWidth()/2);
        playButtonInactive.setPosition(Gdx.graphics.getWidth()/2 - playButtonInactive.getWidth()/2, Gdx.graphics.getHeight()/2);

        playButtonActive = new ImageButton(new TextureRegionDrawable(new TextureRegion(playButtonActiveImage)));
        playButtonActive.setHeight(playButtonActive.getHeight()/2);
        playButtonActive.setWidth(playButtonActive.getWidth()/2);
        playButtonActive.setPosition(Gdx.graphics.getWidth()/2 - playButtonActive.getWidth()/2, Gdx.graphics.getHeight()/2);

        exitButtonInactive = new ImageButton(new TextureRegionDrawable(new TextureRegion(exitButtonInactiveImage)));
        exitButtonInactive.setHeight(exitButtonInactive.getHeight()/2);
        exitButtonInactive.setWidth(exitButtonInactive.getWidth()/2);
        exitButtonInactive.setPosition(Gdx.graphics.getWidth()/2 - exitButtonInactive.getWidth()/2, Gdx.graphics.getHeight()/4);

        exitButtonActive = new ImageButton(new TextureRegionDrawable(new TextureRegion(exitButtonActiveImage)));
        exitButtonActive.setHeight(exitButtonActive.getHeight()/2);
        exitButtonActive.setWidth(exitButtonActive.getWidth()/2);
        exitButtonActive.setPosition(Gdx.graphics.getWidth()/2 - exitButtonActive.getWidth()/2, Gdx.graphics.getHeight()/4);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        playButtonInactive.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                stage.addActor(playButtonActive);
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
                playButtonActive.remove();
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.dispose();
                game.injector.getInstance(Systems.class).list.stream()
                        .map(systemClass -> game.injector.getInstance(systemClass))
                        .forEach(entitySystem -> game.engine.addSystem(entitySystem));
                game.setScreen(game.injector.getInstance(PlayScreen.class));
                return true;
            }
        });

        playButtonActive.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                playButtonActive.remove();
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
                stage.addActor(playButtonInactive);
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.dispose();
                game.injector.getInstance(Systems.class).list.stream()
                        .map(systemClass -> game.injector.getInstance(systemClass))
                        .forEach(entitySystem -> game.engine.addSystem(entitySystem));
                game.setScreen(game.injector.getInstance(PlayScreen.class));
                return true;
            }
        });

        exitButtonInactive.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                stage.addActor(exitButtonActive);
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
                exitButtonActive.remove();
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                UIManager.put("OptionPane.minimumSize",new Dimension(AnimalRacing.V_WIDTH,AnimalRacing.V_HEIGHT/2));
                int n = JOptionPane.showOptionDialog(null,
                        "Do you want to exit the game?",
                        "Warning",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null,
                        options,
                        options);
                if (n == 0) {
                    Gdx.app.exit();
                }
                return true;
            }
        });

        exitButtonActive.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                exitButtonActive.remove();
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
                stage.addActor(exitButtonInactive);
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                UIManager.put("OptionPane.minimumSize",new Dimension(AnimalRacing.V_WIDTH,AnimalRacing.V_HEIGHT/2));
                int n = JOptionPane.showOptionDialog(null,
                        "Do you want to exit the game?",
                        "Warning",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null,
                        options,
                        options);
                if (n == 0) {
                    Gdx.app.exit();
                }
                return true;
            }
        });

        stage.addActor(playButtonInactive);
        stage.addActor(exitButtonInactive);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        stage.act();
        stage.draw();
        batch.end();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
