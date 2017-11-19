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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.google.inject.Inject;

public class InstructionScreen2 extends ScreenAdapter {
    private Texture instruction2;
    private Texture nextInactive;
    private Texture nextActive;
    private Texture previousInactive;
    private Texture previousActive;

    private ImageButton nextButtonInactive;
    private ImageButton nextButtonActive;
    private ImageButton previousButtonInactive;
    private ImageButton previousButtonActive;
    private ImageButton instruction2Image;

    private AnimalRacing game;
    private Batch batch;

    private Stage stage;

    @Inject
    public InstructionScreen2(AssetManager manager, SpriteBatch batch, AnimalRacing game){
        this.batch = batch;
        this.game = game;
        instruction2 = manager.get("instruction2.png", Texture.class);
        nextInactive = manager.get("Next_inactive.png", Texture.class);
        nextActive = manager.get("Next_active.png", Texture.class);
        previousInactive = manager.get("Previous_inactive.png", Texture.class);
        previousActive = manager.get("Previous_active.png", Texture.class);
    }

    public void Instruction(){
        stage = new Stage(new ScreenViewport());
        instruction2Image = new ImageButton(new TextureRegionDrawable(new TextureRegion(instruction2)));
        instruction2Image.setHeight(Gdx.graphics.getHeight());
        instruction2Image.setWidth(Gdx.graphics.getWidth());
        instruction2Image.setPosition(0, 0);
        stage.addActor(instruction2Image);

        nextButtonInactive = new ImageButton(new TextureRegionDrawable(new TextureRegion(nextInactive)));
        nextButtonActive = new ImageButton(new TextureRegionDrawable(new TextureRegion(nextActive)));
        previousButtonInactive = new ImageButton(new TextureRegionDrawable(new TextureRegion(previousInactive)));
        previousButtonActive = new ImageButton(new TextureRegionDrawable(new TextureRegion(previousActive)));

        nextButtonInactive.setHeight(Gdx.graphics.getHeight()/8);
        nextButtonInactive.setWidth(Gdx.graphics.getHeight()/8);
        nextButtonInactive.setPosition(Gdx.graphics.getWidth()/2 - nextButtonInactive.getWidth()/2 + Gdx.graphics.getWidth()*4/11, Gdx.graphics.getHeight()/10);
        nextButtonActive.setHeight(Gdx.graphics.getHeight()/8);
        nextButtonActive.setWidth(Gdx.graphics.getHeight()/8);
        nextButtonActive.setPosition(Gdx.graphics.getWidth()/2 - nextButtonActive.getWidth()/2 + Gdx.graphics.getWidth()*4/11, Gdx.graphics.getHeight()/10);

        previousButtonInactive.setHeight(Gdx.graphics.getHeight()/8);
        previousButtonInactive.setWidth(Gdx.graphics.getHeight()/8);
        previousButtonInactive.setPosition(Gdx.graphics.getWidth()/2 - previousButtonInactive.getWidth()/2 - Gdx.graphics.getWidth()*4/11, Gdx.graphics.getHeight()/10);
        previousButtonActive.setHeight(Gdx.graphics.getHeight()/8);
        previousButtonActive.setWidth(Gdx.graphics.getHeight()/8);
        previousButtonActive.setPosition(Gdx.graphics.getWidth()/2 - previousButtonActive.getWidth()/2 - Gdx.graphics.getWidth()*4/11, Gdx.graphics.getHeight()/10);

        nextButtonInactive.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                stage.addActor(nextButtonActive);
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.dispose();
                game.setScreen(game.injector.getInstance(InstructionScreen3.class));
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
                game.setScreen(game.injector.getInstance(InstructionScreen3.class));
                return true;
            }
        });

        previousButtonInactive.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                stage.addActor(previousButtonActive);
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.dispose();
                game.setScreen(game.injector.getInstance(InstructionScreen1.class));
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
                game.setScreen(game.injector.getInstance(InstructionScreen1.class));
                return true;
            }
        });


        stage.addActor(nextButtonInactive);
        stage.addActor(previousButtonActive);

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
