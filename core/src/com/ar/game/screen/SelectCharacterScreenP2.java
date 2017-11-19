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

public class SelectCharacterScreenP2 extends ScreenAdapter {
    private Texture catInactive;
    private Texture catP2;
    private Texture frogInactive;
    private Texture frogP2;
    private Texture bearInactive;
    private Texture bearP2;
    private Texture pandaInactive;
    private Texture pandaP2;
    private Texture selectCharacter;
    private Texture previousInactive;
    private Texture previousActive;
    private Texture playButtonActiveImage;
    private Texture playButtonInactiveImage;

    private ImageButton catButtonInactive;
    private ImageButton catP2Button;
    private ImageButton frogButtonInactive;
    private ImageButton frogP2Button;
    private ImageButton bearButtonInactive;
    private ImageButton bearP2Button;
    private ImageButton pandaButtonInactive;
    private ImageButton pandaP2Button;
    private ImageButton previousButtonInactive;
    private ImageButton previousButtonActive;
    private ImageButton playButtonActive;
    private ImageButton playButtonInactive;

    private Stage stage;
    private int checkClick=0;

    private SpriteBatch batch;
    private AnimalRacing game;

    public static String characterP2;

    @Inject
    public SelectCharacterScreenP2(AssetManager manager, SpriteBatch batch, AnimalRacing game){
        this.batch = batch;
        this.game = game;
        catInactive = manager.get("cat_inactive.png", Texture.class);
        catP2 = manager.get("cat_P2.png", Texture.class);
        frogInactive = manager.get("frog_inactive.png", Texture.class);
        frogP2 = manager.get("frog_P2.png", Texture.class);
        bearInactive = manager.get("bear_inactive.png", Texture.class);
        bearP2 = manager.get("bear_P2.png", Texture.class);
        pandaInactive = manager.get("panda_inactive.png", Texture.class);
        pandaP2 = manager.get("panda_P2.png", Texture.class);
        selectCharacter = manager.get("selectcharacter_P2.png", Texture.class);
        previousInactive = manager.get("Previous_inactive.png", Texture.class);
        previousActive = manager.get("Previous_active.png", Texture.class);
        playButtonActiveImage = manager.get("Play_active_stroke.png", Texture.class);
        playButtonInactiveImage = manager.get("Play_inactive_stroke.png", Texture.class);
    }

    @Override
    public void show(){
        stage = new Stage(new ScreenViewport());
        previousButtonInactive = new ImageButton(new TextureRegionDrawable(new TextureRegion(previousInactive)));
        previousButtonActive = new ImageButton(new TextureRegionDrawable(new TextureRegion(previousActive)));

        catButtonInactive = new ImageButton(new TextureRegionDrawable(new TextureRegion(catInactive)));
        catButtonInactive.setHeight(Gdx.graphics.getHeight()/4);
        catButtonInactive.setPosition(Gdx.graphics.getWidth()/2 - catButtonInactive.getWidth()/2 - Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/2);

        catP2Button = new ImageButton(new TextureRegionDrawable(new TextureRegion(catP2)));
        catP2Button.setHeight(Gdx.graphics.getHeight()/4);
        catP2Button.setPosition(Gdx.graphics.getWidth()/2 - catP2Button.getWidth()/2 - Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/2);

        frogButtonInactive = new ImageButton(new TextureRegionDrawable(new TextureRegion(frogInactive)));
        frogButtonInactive.setHeight(Gdx.graphics.getHeight()/4);
        frogButtonInactive.setPosition(Gdx.graphics.getWidth()/2 - frogButtonInactive.getWidth()/2 + Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/2);

        frogP2Button = new ImageButton(new TextureRegionDrawable(new TextureRegion(frogP2)));
        frogP2Button.setHeight(Gdx.graphics.getHeight()/4);
        frogP2Button.setPosition(Gdx.graphics.getWidth()/2 - frogP2Button.getWidth()/2 + Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/2);

        bearButtonInactive = new ImageButton(new TextureRegionDrawable(new TextureRegion(bearInactive)));
        bearButtonInactive.setHeight(Gdx.graphics.getHeight()/4);
        bearButtonInactive.setPosition(Gdx.graphics.getWidth()/2 - bearButtonInactive.getWidth()/2 - Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/5);

        bearP2Button = new ImageButton(new TextureRegionDrawable(new TextureRegion(bearP2)));
        bearP2Button.setHeight(Gdx.graphics.getHeight()/4);
        bearP2Button.setPosition(Gdx.graphics.getWidth()/2 - bearP2Button.getWidth()/2 - Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/5);

        pandaButtonInactive = new ImageButton(new TextureRegionDrawable(new TextureRegion(pandaInactive)));
        pandaButtonInactive.setHeight(Gdx.graphics.getHeight()/4);
        pandaButtonInactive.setPosition(Gdx.graphics.getWidth()/2 - pandaButtonInactive.getWidth()/2 + Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/5);

        pandaP2Button = new ImageButton(new TextureRegionDrawable(new TextureRegion(pandaP2)));
        pandaP2Button.setHeight(Gdx.graphics.getHeight()/4);
        pandaP2Button.setPosition(Gdx.graphics.getWidth()/2 - pandaP2Button.getWidth()/2 + Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/5);

        previousButtonInactive.setHeight(Gdx.graphics.getHeight()/8);
        previousButtonInactive.setWidth(Gdx.graphics.getHeight()/8);
        previousButtonInactive.setPosition(Gdx.graphics.getWidth()/2 - previousButtonInactive.getWidth()/2 - Gdx.graphics.getWidth()*4/11, Gdx.graphics.getHeight()/10);
        previousButtonActive.setHeight(Gdx.graphics.getHeight()/8);
        previousButtonActive.setWidth(Gdx.graphics.getHeight()/8);
        previousButtonActive.setPosition(Gdx.graphics.getWidth()/2 - previousButtonActive.getWidth()/2 - Gdx.graphics.getWidth()*4/11, Gdx.graphics.getHeight()/10);

        playButtonInactive = new ImageButton(new TextureRegionDrawable(new TextureRegion(playButtonInactiveImage)));
        playButtonInactive.setHeight(Gdx.graphics.getHeight()/8);
        playButtonInactive.setWidth(Gdx.graphics.getHeight()/8);
        playButtonInactive.setPosition(Gdx.graphics.getWidth()/2 - playButtonInactive.getWidth()/2 + Gdx.graphics.getWidth()*4/11, Gdx.graphics.getHeight()/10);

        playButtonActive = new ImageButton(new TextureRegionDrawable(new TextureRegion(playButtonActiveImage)));
        playButtonActive.setHeight(Gdx.graphics.getHeight()/8);
        playButtonActive.setWidth(Gdx.graphics.getHeight()/8);
        playButtonActive.setPosition(Gdx.graphics.getWidth()/2 - previousButtonActive.getWidth()/2 + Gdx.graphics.getWidth()*4/11, Gdx.graphics.getHeight()/10);

        previousButtonInactive.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                stage.addActor(previousButtonActive);
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.dispose();
                game.setScreen(game.injector.getInstance(SelectCharacterScreenP1.class));
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
                game.setScreen(game.injector.getInstance(SelectCharacterScreenP1.class));
                return true;
            }
        });

        playButtonInactive.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                stage.addActor(playButtonActive);
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
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
                playButtonActive.remove();
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

        //character = ((FileTextureData)catInactive.getTextureData()).getFileHandle().path();
        stage.addActor(catButtonInactive);
        stage.addActor(frogButtonInactive);
        stage.addActor(previousButtonInactive);

        stage.addActor(bearButtonInactive);
        stage.addActor(pandaButtonInactive);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (checkClick == 0) {
            playButtonInactive.remove();
            catButtonInactive.addListener(new ClickListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    stage.addActor(catP2Button);
                    checkClick = 1;
                    characterP2 = "cat";
                    return true;
                }
            });
            frogButtonInactive.addListener(new ClickListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    stage.addActor(frogP2Button);
                    checkClick = 1;
                    characterP2 = "frog";
                    return true;
                }
            });
            bearButtonInactive.addListener(new ClickListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    stage.addActor(bearP2Button);
                    checkClick = 1;
                    characterP2 = "bear";
                    return true;
                }
            });
            pandaButtonInactive.addListener(new ClickListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    stage.addActor(pandaP2Button);
                    checkClick = 1;
                    characterP2 = "panda";
                    return true;
                }
            });

        }

        if (checkClick == 1) {
            frogButtonInactive.clearListeners();
            catButtonInactive.clearListeners();
            bearButtonInactive.clearListeners();
            pandaButtonInactive.clearListeners();
            stage.addActor(playButtonInactive);
            catP2Button.addListener(new ClickListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    catP2Button.remove();
                    stage.addActor(catButtonInactive);
                    checkClick = 0;
                    return true;
                }
            });
            frogP2Button.addListener(new ClickListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    frogP2Button.remove();
                    stage.addActor(frogButtonInactive);
                    checkClick = 0;
                    return true;
                }
            });
            bearP2Button.addListener(new ClickListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    bearP2Button.remove();
                    stage.addActor(bearButtonInactive);
                    checkClick = 0;
                    return true;
                }
            });
            pandaP2Button.addListener(new ClickListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    pandaP2Button.remove();
                    stage.addActor(pandaButtonInactive);
                    checkClick = 0;
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
