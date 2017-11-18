package com.ar.game.screen;

import com.ar.game.AnimalRacing;
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

public class SelectCharacterScreenP1 extends ScreenAdapter {

    private Texture catInactive;
    private Texture catP1;
    private Texture frogInactive;
    private Texture frogP1;
    private Texture bearInactive;
    private Texture bearP1;
    private Texture pandaInactive;
    private Texture pandaP1;
    private Texture selectCharacter;
    private Texture homeInactive;
    private Texture homeActive;
    private Texture nextInactive;
    private Texture nextActive;

    private ImageButton catButtonInactive;
    private ImageButton catP1Button;
    private ImageButton frogButtonInactive;
    private ImageButton frogP1Button;
    private ImageButton bearButtonInactive;
    private ImageButton bearP1Button;
    private ImageButton pandaButtonInactive;
    private ImageButton pandaP1Button;
    private ImageButton homeButtonInactive;
    private ImageButton homeButtonActive;
    private ImageButton nextButtonInactive;
    private ImageButton nextButtonActive;

    private Stage stage;
    private int checkClick=0;

    private SpriteBatch batch;
    private AnimalRacing game;

    public static String characterP1;

    @Inject
    public SelectCharacterScreenP1(AssetManager manager, SpriteBatch batch, AnimalRacing game){
        this.batch = batch;
        this.game = game;
        catInactive = manager.get("cat_inactive.png", Texture.class);
        catP1 = manager.get("cat_P1.png", Texture.class);
        frogInactive = manager.get("frog_inactive.png", Texture.class);
        frogP1 = manager.get("frog_P1.png", Texture.class);
        bearInactive = manager.get("bear_inactive.png", Texture.class);
        bearP1 = manager.get("bear_P1.png", Texture.class);
        pandaInactive = manager.get("panda_inactive.png", Texture.class);
        pandaP1 = manager.get("panda_P1.png", Texture.class);

        selectCharacter = manager.get("selectcharacter_P1.png", Texture.class);

        homeInactive = manager.get("Home_inactive.png", Texture.class);
        homeActive = manager.get("Home_active.png", Texture.class);
        nextInactive = manager.get("Next_inactive.png", Texture.class);
        nextActive = manager.get("Next_active.png", Texture.class);
    }

    @Override
    public void show(){
        stage = new Stage(new ScreenViewport());

        nextButtonInactive = new ImageButton(new TextureRegionDrawable(new TextureRegion(nextInactive)));
        nextButtonActive = new ImageButton(new TextureRegionDrawable(new TextureRegion(nextActive)));

        nextButtonInactive.setHeight(Gdx.graphics.getHeight()/8);
        nextButtonInactive.setWidth(Gdx.graphics.getHeight()/8);
        nextButtonInactive.setPosition(Gdx.graphics.getWidth()/2 - nextButtonInactive.getWidth()/2 + Gdx.graphics.getWidth()*4/11, Gdx.graphics.getHeight()/10);
        nextButtonActive.setHeight(Gdx.graphics.getHeight()/8);
        nextButtonActive.setWidth(Gdx.graphics.getHeight()/8);
        nextButtonActive.setPosition(Gdx.graphics.getWidth()/2 - nextButtonActive.getWidth()/2 + Gdx.graphics.getWidth()*4/11, Gdx.graphics.getHeight()/10);

        catButtonInactive = new ImageButton(new TextureRegionDrawable(new TextureRegion(catInactive)));
        catButtonInactive.setHeight(Gdx.graphics.getHeight()/4);
        catButtonInactive.setPosition(Gdx.graphics.getWidth()/2 - catButtonInactive.getWidth()/2 - Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/2);

        catP1Button = new ImageButton(new TextureRegionDrawable(new TextureRegion(catP1)));
        catP1Button.setHeight(Gdx.graphics.getHeight()/4);
        catP1Button.setPosition(Gdx.graphics.getWidth()/2 - catP1Button.getWidth()/2 - Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/2);

        frogButtonInactive = new ImageButton(new TextureRegionDrawable(new TextureRegion(frogInactive)));
        frogButtonInactive.setHeight(Gdx.graphics.getHeight()/4);
        frogButtonInactive.setPosition(Gdx.graphics.getWidth()/2 - frogButtonInactive.getWidth()/2 + Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/2);

        frogP1Button = new ImageButton(new TextureRegionDrawable(new TextureRegion(frogP1)));
        frogP1Button.setHeight(Gdx.graphics.getHeight()/4);
        frogP1Button.setPosition(Gdx.graphics.getWidth()/2 - frogP1Button.getWidth()/2 + Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/2);

        bearButtonInactive = new ImageButton(new TextureRegionDrawable(new TextureRegion(bearInactive)));
        bearButtonInactive.setHeight(Gdx.graphics.getHeight()/4);
        bearButtonInactive.setPosition(Gdx.graphics.getWidth()/2 - bearButtonInactive.getWidth()/2 - Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/5);

        bearP1Button = new ImageButton(new TextureRegionDrawable(new TextureRegion(bearP1)));
        bearP1Button.setHeight(Gdx.graphics.getHeight()/4);
        bearP1Button.setPosition(Gdx.graphics.getWidth()/2 - bearP1Button.getWidth()/2 - Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/5);

        pandaButtonInactive = new ImageButton(new TextureRegionDrawable(new TextureRegion(pandaInactive)));
        pandaButtonInactive.setHeight(Gdx.graphics.getHeight()/4);
        pandaButtonInactive.setPosition(Gdx.graphics.getWidth()/2 - pandaButtonInactive.getWidth()/2 + Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/5);

        pandaP1Button = new ImageButton(new TextureRegionDrawable(new TextureRegion(pandaP1)));
        pandaP1Button.setHeight(Gdx.graphics.getHeight()/4);
        pandaP1Button.setPosition(Gdx.graphics.getWidth()/2 - pandaP1Button.getWidth()/2 + Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/5);

        homeButtonInactive = new ImageButton(new TextureRegionDrawable(new TextureRegion(homeInactive)));
        homeButtonActive = new ImageButton(new TextureRegionDrawable(new TextureRegion(homeActive)));

        homeButtonInactive.setHeight(Gdx.graphics.getHeight()/8);
        homeButtonInactive.setWidth(Gdx.graphics.getHeight()/8);
        homeButtonActive.setHeight(Gdx.graphics.getHeight()/8);
        homeButtonActive.setWidth(Gdx.graphics.getHeight()/8);
        homeButtonInactive.setPosition(Gdx.graphics.getWidth()/2 - homeButtonInactive.getWidth()/2 - Gdx.graphics.getWidth()*4/11, Gdx.graphics.getHeight()/10);
        homeButtonActive.setPosition(Gdx.graphics.getWidth()/2 - homeButtonActive.getWidth()/2 - Gdx.graphics.getWidth()*4/11, Gdx.graphics.getHeight()/10);

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



        //character = ((FileTextureData)catInactive.getTextureData()).getFileHandle().path();
        stage.addActor(homeButtonInactive);
        stage.addActor(nextButtonInactive);
        stage.addActor(catButtonInactive);
        stage.addActor(frogButtonInactive);
        stage.addActor(bearButtonInactive);
        stage.addActor(pandaButtonInactive);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (checkClick == 0) {
            nextButtonInactive.clearListeners();
            nextButtonActive.clearListeners();
            catButtonInactive.addListener(new ClickListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    stage.addActor(catP1Button);
                    checkClick = 1;
                    characterP1 = "cat";
                    return true;
                }
            });
            frogButtonInactive.addListener(new ClickListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    stage.addActor(frogP1Button);
                    checkClick = 1;
                    characterP1 = "frog";
                    return true;
                }
            });
            bearButtonInactive.addListener(new ClickListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    stage.addActor(bearP1Button);
                    checkClick = 1;
                    characterP1 = "bear";
                    return true;
                }
            });
            pandaButtonInactive.addListener(new ClickListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    stage.addActor(pandaP1Button);
                    checkClick = 1;
                    characterP1 = "panda";
                    return true;
                }
            });
        }

        if (checkClick == 1) {
            frogButtonInactive.clearListeners();
            catButtonInactive.clearListeners();
            bearButtonInactive.clearListeners();
            pandaButtonInactive.clearListeners();
            catP1Button.addListener(new ClickListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    catP1Button.remove();
                    stage.addActor(catButtonInactive);
                    checkClick = 0;
                    return true;
                }
            });
            frogP1Button.addListener(new ClickListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    frogP1Button.remove();
                    stage.addActor(frogButtonInactive);
                    checkClick = 0;
                    return true;
                }
            });
            bearP1Button.addListener(new ClickListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    bearP1Button.remove();
                    stage.addActor(bearButtonInactive);
                    checkClick = 0;
                    return true;
                }
            });
            pandaP1Button.addListener(new ClickListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    pandaP1Button.remove();
                    stage.addActor(pandaButtonInactive);
                    checkClick = 0;
                    return true;
                }
            });
            nextButtonInactive.addListener(new ClickListener() {
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                    stage.addActor(nextButtonActive);
                }
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    game.dispose();
                    game.setScreen(game.injector.getInstance(SelectCharacterScreenP2.class));
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
                    game.setScreen(game.injector.getInstance(SelectCharacterScreenP2.class));
                    return true;
                }
            });
        }
        batch.begin();
        batch.draw(selectCharacter, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
