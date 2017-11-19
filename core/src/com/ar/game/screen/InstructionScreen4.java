package com.ar.game.screen;

import com.ar.game.AnimalRacing;
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

public class InstructionScreen4 extends ScreenAdapter {

    private Texture instruction4;
    private Texture previousInactive;
    private Texture previousActive;
    private Texture homeInactive;
    private Texture homeActive;

    private ImageButton previousButtonInactive;
    private ImageButton previousButtonActive;
    private ImageButton homeButtonInactive;
    private ImageButton homeButtonActive;
    private ImageButton instruction4Image;

    private AnimalRacing game;
    private Batch batch;

    private Stage stage;

    @Inject
    public InstructionScreen4(AssetManager manager, SpriteBatch batch, AnimalRacing game){
        this.batch = batch;
        this.game = game;
        instruction4 = manager.get("instruction4.png", Texture.class);
        previousInactive = manager.get("Previous_inactive.png", Texture.class);
        previousActive = manager.get("Previous_active.png", Texture.class);
        homeInactive = manager.get("Home_inactive.png", Texture.class);
        homeActive = manager.get("Home_active.png", Texture.class);
    }

    public void Instruction(){
        stage = new Stage(new ScreenViewport());

        instruction4Image = new ImageButton(new TextureRegionDrawable(new TextureRegion(instruction4)));
        instruction4Image.setHeight(Gdx.graphics.getHeight());
        instruction4Image.setWidth(Gdx.graphics.getWidth());
        instruction4Image.setPosition(0, 0);
        stage.addActor(instruction4Image);

        previousButtonInactive = new ImageButton(new TextureRegionDrawable(new TextureRegion(previousInactive)));
        previousButtonActive = new ImageButton(new TextureRegionDrawable(new TextureRegion(previousActive)));
        homeButtonInactive = new ImageButton(new TextureRegionDrawable(new TextureRegion(homeInactive)));
        homeButtonActive = new ImageButton(new TextureRegionDrawable(new TextureRegion(homeActive)));

        previousButtonInactive.setHeight(Gdx.graphics.getHeight()/8);
        previousButtonInactive.setWidth(Gdx.graphics.getHeight()/8);
        previousButtonInactive.setPosition(Gdx.graphics.getWidth()/2 - previousButtonInactive.getWidth()/2 - Gdx.graphics.getWidth()*4/11, Gdx.graphics.getHeight()/10);
        previousButtonActive.setHeight(Gdx.graphics.getHeight()/8);
        previousButtonActive.setWidth(Gdx.graphics.getHeight()/8);
        previousButtonActive.setPosition(Gdx.graphics.getWidth()/2 - previousButtonActive.getWidth()/2 - Gdx.graphics.getWidth()*4/11, Gdx.graphics.getHeight()/10);

        homeButtonInactive.setHeight(Gdx.graphics.getHeight()/8);
        homeButtonInactive.setWidth(Gdx.graphics.getHeight()/8);
        homeButtonActive.setHeight(Gdx.graphics.getHeight()/8);
        homeButtonActive.setWidth(Gdx.graphics.getHeight()/8);

        homeButtonInactive.setPosition(Gdx.graphics.getWidth()/2 - homeButtonInactive.getWidth()/2 + Gdx.graphics.getWidth()*4/11, Gdx.graphics.getHeight()/10);
        homeButtonActive.setPosition(Gdx.graphics.getWidth()/2 - homeButtonActive.getWidth()/2 + Gdx.graphics.getWidth()*4/11, Gdx.graphics.getHeight()/10);

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

        previousButtonInactive.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                stage.addActor(previousButtonActive);
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.dispose();
                game.setScreen(game.injector.getInstance(InstructionScreen3.class));
                return true;
            }
        });

        previousButtonActive.addListener(new ClickListener() {
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
                previousButtonActive.remove();
                stage.addActor(previousButtonInactive);
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.dispose();
                game.setScreen(game.injector.getInstance(InstructionScreen3.class));
                return true;
            }
        });

        stage.addActor(homeButtonInactive);
        stage.addActor(previousButtonInactive);

    }

    @Override
    public void show(){
        stage = new Stage(new ScreenViewport());
        Instruction();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        batch.begin();
        stage.draw();
        batch.end();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
