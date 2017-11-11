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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.google.inject.Inject;

import javax.swing.*;
import java.awt.*;

public class MainMenuScreen extends ScreenAdapter {

    private Texture exitButtonActive;
    private Texture exitButtonInactive;
    private Texture playButtonActive;
    private Texture playButtonInactive;
    private ImageButton playButton;
    private ImageButton exitButton;

    private SpriteBatch batch;
    private AnimalRacing game;


    Object[] options = {"Yes", "No"};

    private Stage stage;

    @Inject
    public MainMenuScreen (AssetManager manager, SpriteBatch batch, AnimalRacing game){
        this.batch = batch;
        this.game = game;
        playButtonActive = manager.get("Play_active.jpg", Texture.class);
        playButtonInactive = manager.get("Play_inactive.jpg", Texture.class);
        exitButtonActive = manager.get("Exit_active.jpg", Texture.class);
        exitButtonInactive = manager.get("Exit_inactive.jpg", Texture.class);
    }

    @Override
    public void show(){

        playButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(playButtonInactive)));
        playButton.setHeight(playButton.getHeight()/2);
        playButton.setWidth(playButton.getWidth()/2);
        playButton.setPosition(Gdx.graphics.getWidth()/2 - playButton.getWidth()/2, Gdx.graphics.getHeight()/2);

        exitButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(exitButtonInactive)));
        exitButton.setHeight(exitButton.getHeight()/2);
        exitButton.setWidth(exitButton.getWidth()/2);
        exitButton.setPosition(Gdx.graphics.getWidth()/2 - exitButton.getWidth()/2, Gdx.graphics.getHeight()/4);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        playButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.dispose();
                game.injector.getInstance(Systems.class).list.stream()
                        .map(systemClass -> game.injector.getInstance(systemClass))
                        .forEach(entitySystem -> game.engine.addSystem(entitySystem));
                game.setScreen(game.injector.getInstance(MainGameScreen.class));
                return true;
            }
            public void touchDragged(InputEvent event, float x, float y, int pointer){}

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

            }
        });

        exitButton.addListener(new InputListener() {
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

            public void touchDragged(InputEvent event, float x, float y, int pointer){}

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

            }
        });

        stage.addActor(playButton);
        stage.addActor(exitButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        batch.end();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
