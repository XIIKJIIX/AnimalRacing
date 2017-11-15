package com.ar.game.screen;

import com.ar.game.AnimalRacing;
import com.ar.game.system.Systems;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.google.inject.Inject;
import javax.swing.*;
import java.awt.*;

public class MainMenuScreen extends ScreenAdapter {

    private Texture exitButtonActiveImage;
    private Texture exitButtonInactiveImage;
    private Texture playButtonActiveImage;
    private Texture playButtonInactiveImage;
    private Texture instructionButtonActiveImage;
    private Texture instructionButtonInactiveImage;
    private Texture parallax1;
    private Texture parallax2;
    private Texture parallax3;
    private Texture parallax4;
    private Texture parallax5;
    private Texture parallax6;
    private ImageButton playButtonActive;
    private ImageButton playButtonInactive;
    private ImageButton instructionButtonActive;
    private ImageButton instructionButtonInactive;
    private ImageButton exitButtonActive;
    private ImageButton exitButtonInactive;

    private Game game2;
    private OrthographicCamera camera;

    private SpriteBatch batch;
    private AnimalRacing game;

    Object[] options = {"Yes", "No"};

    private Stage stage;

    @Inject
    public MainMenuScreen (AssetManager manager, SpriteBatch batch, AnimalRacing game){
        this.batch = batch;
        this.game = game;
        playButtonActiveImage = manager.get("Play_active.png", Texture.class);
        playButtonInactiveImage = manager.get("Play_inactive.png", Texture.class);
        exitButtonActiveImage = manager.get("Exit_active.png", Texture.class);
        exitButtonInactiveImage = manager.get("Exit_inactive.png", Texture.class);
        instructionButtonActiveImage = manager.get("Instruction_active.png", Texture.class);
        instructionButtonInactiveImage = manager.get("Instruction_inactive.png", Texture.class);
        parallax1 = manager.get("parallax1.png", Texture.class);
        parallax2 = manager.get("parallax2.png", Texture.class);
        parallax3 = manager.get("parallax3.png", Texture.class);
        parallax4 = manager.get("parallax4.png", Texture.class);
        parallax5 = manager.get("parallax5.png", Texture.class);
        parallax6 = manager.get("parallax6.png", Texture.class);

    }

    public void Composition(Game aGame) {
        game2 = aGame;
        stage = new Stage(new ScreenViewport());
        camera = (OrthographicCamera) stage.getViewport().getCamera();

        Array<Texture> textures = new Array<Texture>();
        textures.add(parallax1);
        textures.get(textures.size-1).setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
        textures.add(parallax2);
        textures.get(textures.size-1).setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
        textures.add(parallax3);
        textures.get(textures.size-1).setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
        textures.add(parallax4);
        textures.get(textures.size-1).setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
        textures.add(parallax5);
        textures.get(textures.size-1).setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
        textures.add(parallax6);
        textures.get(textures.size-1).setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
        ParallaxBackground parallaxBackground = new ParallaxBackground(textures);
        parallaxBackground.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        parallaxBackground.setSpeed(1);
        stage.addActor(parallaxBackground);

        playButtonInactive = new ImageButton(new TextureRegionDrawable(new TextureRegion(playButtonInactiveImage)));
        playButtonInactive.setHeight(Gdx.graphics.getHeight()/5);
        playButtonInactive.setWidth(Gdx.graphics.getHeight()/5);
        playButtonInactive.setPosition(Gdx.graphics.getWidth()/2 - playButtonInactive.getWidth()/2 - Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/8);

        playButtonActive = new ImageButton(new TextureRegionDrawable(new TextureRegion(playButtonActiveImage)));
        playButtonActive.setHeight(Gdx.graphics.getHeight()/5);
        playButtonActive.setWidth(Gdx.graphics.getHeight()/5);
        playButtonActive.setPosition(Gdx.graphics.getWidth()/2 - playButtonInactive.getWidth()/2 - Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/8);

        instructionButtonInactive = new ImageButton(new TextureRegionDrawable(new TextureRegion(instructionButtonInactiveImage)));
        instructionButtonInactive.setHeight(Gdx.graphics.getHeight()/5);
        instructionButtonInactive.setWidth(Gdx.graphics.getHeight()/5);
        instructionButtonInactive.setPosition(Gdx.graphics.getWidth()/2 - playButtonInactive.getWidth()/2, Gdx.graphics.getHeight()/8);

        instructionButtonActive = new ImageButton(new TextureRegionDrawable(new TextureRegion(instructionButtonActiveImage)));
        instructionButtonActive.setHeight(Gdx.graphics.getHeight()/5);
        instructionButtonActive.setWidth(Gdx.graphics.getHeight()/5);
        instructionButtonActive.setPosition(Gdx.graphics.getWidth()/2 - playButtonInactive.getWidth()/2, Gdx.graphics.getHeight()/8);

        exitButtonInactive = new ImageButton(new TextureRegionDrawable(new TextureRegion(exitButtonInactiveImage)));
        exitButtonInactive.setHeight(Gdx.graphics.getHeight()/5);
        exitButtonInactive.setWidth(Gdx.graphics.getHeight()/5);
        exitButtonInactive.setPosition(Gdx.graphics.getWidth()/2 - playButtonInactive.getWidth()/2 + Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/8);

        exitButtonActive = new ImageButton(new TextureRegionDrawable(new TextureRegion(exitButtonActiveImage)));
        exitButtonActive.setHeight(Gdx.graphics.getHeight()/5);
        exitButtonActive.setWidth(Gdx.graphics.getHeight()/5);
        exitButtonActive.setPosition(Gdx.graphics.getWidth()/2 - playButtonInactive.getWidth()/2 + Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/8);

        instructionButtonInactive.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                stage.addActor(instructionButtonActive);
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
                instructionButtonActive.remove();
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.dispose();
                game.injector.getInstance(Systems.class).list.stream()
                        .map(systemClass -> game.injector.getInstance(systemClass))
                        .forEach(entitySystem -> game.engine.addSystem(entitySystem));
                game.setScreen(game.injector.getInstance(MainGameScreen.class));
                return true;
            }
        });

        instructionButtonActive.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                instructionButtonActive.remove();
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
                stage.addActor(instructionButtonInactive);
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.dispose();
                game.injector.getInstance(Systems.class).list.stream()
                        .map(systemClass -> game.injector.getInstance(systemClass))
                        .forEach(entitySystem -> game.engine.addSystem(entitySystem));
                game.setScreen(game.injector.getInstance(MainGameScreen.class));
                return true;
            }
        });

        stage.addActor(instructionButtonInactive);

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
                game.setScreen(game.injector.getInstance(MainGameScreen.class));
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
                game.setScreen(game.injector.getInstance(MainGameScreen.class));
                return true;
            }
        });

        stage.addActor(playButtonInactive);

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

        stage.addActor(exitButtonInactive);

    }

    @Override
    public void show(){
        stage = new Stage(new ScreenViewport());
        Composition(game);
        Gdx.input.setInputProcessor(stage);

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
