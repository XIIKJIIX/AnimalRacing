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

public class InstructionScreen1 extends ScreenAdapter {

    private Texture instruction1;
    private Texture nextInactive;
    private Texture nextActive;
    private Texture homeInactive;
    private Texture homeActive;

    private ImageButton nextButtonInactive;
    private ImageButton nextButtonActive;
    private ImageButton homeButtonInactive;
    private ImageButton homeButtonActive;
    private ImageButton instruction1Image;

    private AnimalRacing game;
    private Batch batch;

    private Stage stage;

    @Inject
    public InstructionScreen1(AssetManager manager, SpriteBatch batch, AnimalRacing game){
        this.batch = batch;
        this.game = game;
        instruction1 = manager.get("instruction1.png", Texture.class);
        nextInactive = manager.get("Next_inactive.png", Texture.class);
        nextActive = manager.get("Next_active.png", Texture.class);
        homeInactive = manager.get("Home_inactive.png", Texture.class);
        homeActive = manager.get("Home_active.png", Texture.class);
    }

    public void Instruction(){
        stage = new Stage(new ScreenViewport());
        instruction1Image = new ImageButton(new TextureRegionDrawable(new TextureRegion(instruction1)));
        instruction1Image.setHeight(Gdx.graphics.getHeight());
        instruction1Image.setWidth(Gdx.graphics.getWidth());
        instruction1Image.setPosition(0, 0);
        stage.addActor(instruction1Image);


        nextButtonInactive = new ImageButton(new TextureRegionDrawable(new TextureRegion(nextInactive)));
        nextButtonActive = new ImageButton(new TextureRegionDrawable(new TextureRegion(nextActive)));
        homeButtonInactive = new ImageButton(new TextureRegionDrawable(new TextureRegion(homeInactive)));
        homeButtonActive = new ImageButton(new TextureRegionDrawable(new TextureRegion(homeActive)));

        nextButtonInactive.setHeight(Gdx.graphics.getHeight()/8);
        nextButtonInactive.setWidth(Gdx.graphics.getHeight()/8);
        nextButtonInactive.setPosition(Gdx.graphics.getWidth()/2 - nextButtonInactive.getWidth()/2 + Gdx.graphics.getWidth()*4/11, Gdx.graphics.getHeight()/10);
        nextButtonActive.setHeight(Gdx.graphics.getHeight()/8);
        nextButtonActive.setWidth(Gdx.graphics.getHeight()/8);
        nextButtonActive.setPosition(Gdx.graphics.getWidth()/2 - nextButtonActive.getWidth()/2 + Gdx.graphics.getWidth()*4/11, Gdx.graphics.getHeight()/10);

        homeButtonInactive.setHeight(Gdx.graphics.getHeight()/8);
        homeButtonInactive.setWidth(Gdx.graphics.getHeight()/8);
        homeButtonActive.setHeight(Gdx.graphics.getHeight()/8);
        homeButtonActive.setWidth(Gdx.graphics.getHeight()/8);

        homeButtonInactive.setPosition(Gdx.graphics.getWidth()/2 - homeButtonInactive.getWidth()/2 - Gdx.graphics.getWidth()*4/11, Gdx.graphics.getHeight()/10);
        homeButtonActive.setPosition(Gdx.graphics.getWidth()/2 - homeButtonActive.getWidth()/2 - Gdx.graphics.getWidth()*4/11, Gdx.graphics.getHeight()/10);


        nextButtonInactive.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                stage.addActor(nextButtonActive);
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.dispose();
                game.setScreen(game.injector.getInstance(InstructionScreen2.class));
                return true;
            }
        });

        nextButtonActive.addListener(new ClickListener() {
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
                nextButtonActive.remove();
                stage.addActor(nextButtonInactive);
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.dispose();
                game.setScreen(game.injector.getInstance(InstructionScreen2.class));
                return true;
            }
        });

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

        stage.addActor(nextButtonInactive);
        stage.addActor(homeButtonInactive);

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
