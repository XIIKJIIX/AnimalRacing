package com.ar.game.screen;

import com.ar.game.AnimalRacing;
import com.ar.game.system.Systems;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.inject.Inject;

import javax.swing.*;
import java.awt.*;

public class MainMenuScreen extends ScreenAdapter {

    private Texture exitButtonActive;
    private Texture exitButtonInactive;
    private Texture playButtonActive;
    private Texture playButtonInactive;
    private SpriteBatch batch;
    private AnimalRacing game;
    private static final int EXIT_BUTTON_WIDTH = 300;
    private static final int EXIT_BUTTON_HEIGHT = 100;
    private static final int PLAY_BUTTON_WIDTH = 300;
    private static final int PLAY_BUTTON_HEIGHT = 100;
    private static final int EXIT_BUTTON_Y = 100;
    private static final int PLAY_BUTTON_Y = 300;

    Object[] options = {"Yes", "No"};


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
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        int x = AnimalRacing.V_WIDTH - EXIT_BUTTON_WIDTH/2;
        if (Gdx.input.getX() < x + EXIT_BUTTON_WIDTH && Gdx.input.getX() > x &&
                AnimalRacing.V_HEIGHT*2 - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT &&
                AnimalRacing.V_HEIGHT*2 - Gdx.input.getY()> EXIT_BUTTON_Y) {
            batch.draw(exitButtonActive, x, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);

            if (Gdx.input.isTouched()){
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
            }
        }

        else {
            batch.draw(exitButtonInactive, x, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        }

        if (Gdx.input.getX() < x + PLAY_BUTTON_WIDTH && Gdx.input.getX() > x &&
                AnimalRacing.V_HEIGHT*2 - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT &&
                AnimalRacing.V_HEIGHT*2 - Gdx.input.getY() > PLAY_BUTTON_Y){
            batch.draw(playButtonActive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);

            if (Gdx.input.isTouched()){
                this.dispose();
                game.injector.getInstance(Systems.class).list.stream()
                        .map(systemClass -> game.injector.getInstance(systemClass))
                        .forEach(entitySystem -> game.engine.addSystem(entitySystem));
                game.setScreen(game.injector.getInstance(MainGameScreen.class));
            }
        }

        else {
            batch.draw(playButtonInactive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        }

        batch.end();
    }

    @Override
    public void dispose() {
    }
}
