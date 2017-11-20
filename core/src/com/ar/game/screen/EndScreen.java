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

import java.io.File;
import java.util.ArrayList;

public class EndScreen extends ScreenAdapter {

    private Texture player1win;
    private Texture player2win;
    private Texture homeInactive;
    private Texture homeActive;
    private Texture player1;
    private Texture player2;
    private Texture catInactive;
    private Texture frogInactive;
    private Texture bearInactive;
    private Texture pandaInactive;

    private ImageButton homeButtonInactive;
    private ImageButton homeButtonActive;
    private ImageButton player1Image;
    private ImageButton player2Image;
    private ImageButton player1winImage;
    private ImageButton player2winImage;

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
        catInactive = manager.get("cat_inactive.png", Texture.class);
        frogInactive = manager.get("frog_inactive.png", Texture.class);
        bearInactive = manager.get("bear_inactive.png", Texture.class);
        pandaInactive = manager.get("panda_inactive.png", Texture.class);
    }

    @Override
    public void show(){
        stage = new Stage(new ScreenViewport());
        player1winImage = new ImageButton(new TextureRegionDrawable(new TextureRegion(player1win)));
        player2winImage = new ImageButton(new TextureRegionDrawable(new TextureRegion(player2win)));

        homeButtonInactive = new ImageButton(new TextureRegionDrawable(new TextureRegion(homeInactive)));
        homeButtonActive = new ImageButton(new TextureRegionDrawable(new TextureRegion(homeActive)));

        homeButtonInactive.setHeight(Gdx.graphics.getHeight()/6);
        homeButtonInactive.setWidth(Gdx.graphics.getHeight()/6);
        homeButtonActive.setHeight(Gdx.graphics.getHeight()/6);
        homeButtonActive.setWidth(Gdx.graphics.getHeight()/6);
        homeButtonInactive.setPosition(Gdx.graphics.getWidth()/2 - homeButtonInactive.getWidth()/2, Gdx.graphics.getHeight()/6);
        homeButtonActive.setPosition(Gdx.graphics.getWidth()/2 - homeButtonActive.getWidth()/2, Gdx.graphics.getHeight()/6);

        homeButtonInactive.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                stage.addActor(homeButtonActive);
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                restartApplication();
                return true;
            }
        });

        homeButtonActive.addListener(new ClickListener() {
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
                homeButtonActive.remove();
                stage.addActor(homeButtonInactive);
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                restartApplication();
                return true;
            }
        });

        if (SelectCharacterScreenP1.characterP1 == "cat"){
            player1 = catInactive;
        }
        else if (SelectCharacterScreenP1.characterP1 == "frog") {
            player1 = frogInactive;
        }
        else if (SelectCharacterScreenP1.characterP1 == "bear") {
            player1 = bearInactive;
        }
        else if (SelectCharacterScreenP1.characterP1 == "panda") {
            player1 = pandaInactive;
        }
        if (SelectCharacterScreenP2.characterP2 == "cat"){
            player2 = catInactive;
        }
        else if (SelectCharacterScreenP2.characterP2 == "frog") {
            player2 = frogInactive;
        }
        else if (SelectCharacterScreenP2.characterP2 == "bear") {
            player2 = bearInactive;
        }
        else if (SelectCharacterScreenP2.characterP2 == "panda") {
            player2 = pandaInactive;
        }
        player1Image = new ImageButton(new TextureRegionDrawable(new TextureRegion(player1)));
        player2Image = new ImageButton(new TextureRegionDrawable(new TextureRegion(player2)));
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if (PlayScreen.winnerName.equals("Player2")) { //player1 win (Swap Case to fix)
            player1winImage.setHeight(Gdx.graphics.getHeight());
            player1winImage.setPosition(0,0);
            stage.addActor(player1winImage);
        }
        else {
            player2winImage.setHeight(Gdx.graphics.getHeight());
            player2winImage.setPosition(0,0);
            stage.addActor(player2winImage);
        }
        player1Image.setPosition(Gdx.graphics.getWidth()/2 - player1.getWidth()/2 - Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/2 + player1.getHeight()/2);
        stage.addActor(player1Image);
        player2Image.setPosition(Gdx.graphics.getWidth()/2 - player1.getWidth()/2 - Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/2 - player1.getHeight()/2);
        stage.addActor(player2Image);
        stage.addActor(homeButtonInactive);
        batch.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
    }

    private void restartApplication()
    {
        final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
        try {
            File currentJar = new File(PlayScreen.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            if(!currentJar.getName().endsWith(".jar")) {
                System.out.println("not jar so Quit instead");
                Gdx.app.exit();
            }
            final ArrayList<String> command = new ArrayList<>();
            command.add(javaBin);
            command.add("-jar");
            command.add(currentJar.getPath());

            final ProcessBuilder builder = new ProcessBuilder(command);
            builder.start();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
