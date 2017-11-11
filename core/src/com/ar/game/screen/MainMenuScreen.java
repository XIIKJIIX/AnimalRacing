package com.ar.game.screen;

import com.ar.game.AnimalRacing;
import com.ar.game.GameModule;
import com.ar.game.system.Systems;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.google.inject.Guice;
import com.google.inject.Injector;

import javax.swing.*;
import java.awt.*;

public class MainMenuScreen extends ScreenAdapter {

    AnimalRacing game;

    Texture exitButtonActive;
    Texture exitButtonInactive;
    Texture playButtonActive;
    Texture playButtonInactive;
    private static final int EXIT_BUTTON_WIDTH = 300;
    private static final int EXIT_BUTTON_HEIGHT = 100;
    private static final int PLAY_BUTTON_WIDTH = 300;
    private static final int PLAY_BUTTON_HEIGHT = 100;
    private static final int EXIT_BUTTON_Y = 100;
    private static final int PLAY_BUTTON_Y = 300;

    Object[] options = {"Yes", "No"};


    public Injector injector;

    public MainMenuScreen (AnimalRacing game){
        this.game = game;
        playButtonActive = new Texture("Play_active.jpg");
        playButtonInactive = new Texture("Play_inactive.jpg");
        exitButtonActive = new Texture("Exit_active.jpg");
        exitButtonInactive = new Texture("Exit_inactive.jpg");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        int x = AnimalRacing.V_WIDTH - EXIT_BUTTON_WIDTH/2;
        if (Gdx.input.getX() < x + EXIT_BUTTON_WIDTH && Gdx.input.getX() > x &&
                AnimalRacing.V_HEIGHT*2 - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT &&
                AnimalRacing.V_HEIGHT*2 - Gdx.input.getY()> EXIT_BUTTON_Y) {
            game.batch.draw(exitButtonActive, x, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);

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
            game.batch.draw(exitButtonInactive, x, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        }

        if (Gdx.input.getX() < x + PLAY_BUTTON_WIDTH && Gdx.input.getX() > x &&
                AnimalRacing.V_HEIGHT*2 - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT &&
                AnimalRacing.V_HEIGHT*2 - Gdx.input.getY() > PLAY_BUTTON_Y){
            game.batch.draw(playButtonActive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);

            if (Gdx.input.isTouched()){
                this.dispose();
                injector = Guice.createInjector(new GameModule(game));
                injector.getInstance(Systems.class).list.stream()
                        .map(systemClass -> injector.getInstance(systemClass))
                        .forEach(entitySystem -> game.engine.addSystem(entitySystem));
                game.setScreen(injector.getInstance(MainGameScreen.class));
            }
        }

        else {
            game.batch.draw(playButtonInactive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        }

        game.batch.end();
    }

    @Override
    public void dispose() {
    }
}
