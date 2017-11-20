package com.ar.game;

import com.ar.game.handler.CollisionDetector;
import com.ar.game.system.*;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import java.util.Arrays;

import static com.ar.game.constant.B2Dvars.PPM;

public class GameModule implements Module {
    private AnimalRacing animalRacing;

    public GameModule(AnimalRacing animalRacing) {
        this.animalRacing = animalRacing;
    }

    @Override
    public void configure(Binder binder) {
        binder.requireAtInjectOnConstructors();
        binder.requireExactBindingAnnotations();
        binder.bind(SpriteBatch.class).toInstance(animalRacing.batch);
        binder.bind(AnimalRacing.class).toInstance(animalRacing);
    }

    @Provides
    @Singleton
    public Engine engine() {
        return animalRacing.engine;
    }

    @Provides
    @Singleton
    public World world() {
        Box2D.init();
        World world = new World(new Vector2(0F, -9.81F), true);
        world.setContactListener(new CollisionDetector());
        return world;
    }

    @Provides
    @Singleton
    public OrthographicCamera camera() {

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, AnimalRacing.V_WIDTH / PPM , AnimalRacing.V_HEIGHT / PPM);
        camera.update();
        return camera;
    }

    @Provides
    @Singleton
    public Systems systems() {
        return new Systems(Arrays.asList(
                CollisionSystem.class,
                PlayerControlSystem.class,
//                PhysicsDebugSystem.class,
                PhysicsSystem.class,
                PhysicsSynchronizationSystem.class,
                DyingSystem.class,
//                CameraSystem.class,
                HealthSystem.class,
                AnimateSystem.class,
                PlayerStatusSystem.class,
                CleanupSystem.class,
                SkillUpdateSystem.class,
                StateUpdateSystem.class
        ));
    }

    @Provides
    @Singleton
    public AssetManager asset() {
        AssetManager manager = new AssetManager();
        manager.load("blank.png", Texture.class);
        manager.load("skills/iceball.png", Texture.class);
        manager.load("skills/bomb.png", Texture.class);
        manager.load("skills/potion.png", Texture.class);
        manager.load("skills/treetop.png", Texture.class);
        manager.load("skills/quarz.png", Texture.class);
        manager.load("skills/exort.png", Texture.class);
        manager.load("sounds/Invoke.mp3", Sound.class);
        manager.load("sounds/explosion.mp3", Sound.class);
        manager.load("sounds/heal.mp3", Sound.class);
        manager.load("sounds/Crystal_Maiden_preattack2.mp3", Sound.class);
        manager.load("sounds/Cold_Embrace_target.mp3", Sound.class);
        manager.load("sounds/tick.mp3", Sound.class);
        
        //MainMenuScreen
        manager.load("Play_active.png", Texture.class);
        manager.load("Play_inactive.png", Texture.class);
        manager.load("Instruction_active.png", Texture.class);
        manager.load("Instruction_inactive.png", Texture.class);
        manager.load("Exit_active.png", Texture.class);
        manager.load("Exit_inactive.png", Texture.class);
        manager.load("logo.png", Texture.class);
        manager.load("parallax0.png", Texture.class);
        manager.load("parallax1.png", Texture.class);
        manager.load("parallax2.png", Texture.class);
        manager.load("parallax3.png", Texture.class);
        manager.load("parallax4.png", Texture.class);
        manager.load("parallax5.png", Texture.class);
        manager.load("parallax6.png", Texture.class);
        manager.load("parallax7.png", Texture.class);
        manager.load("parallax8.png", Texture.class);
        manager.load("parallax9.png", Texture.class);
        manager.load("parallax10.png", Texture.class);

        //SelectCharacterScreenP1
        manager.load("selectcharacter_P1.png", Texture.class);
        manager.load("selectcharacter_P2.png", Texture.class);
        manager.load("cat_inactive.png", Texture.class);
        manager.load("cat_P1.png", Texture.class);
        manager.load("cat_P2.png", Texture.class);
        manager.load("frog_inactive.png", Texture.class);
        manager.load("frog_P1.png", Texture.class);
        manager.load("frog_P2.png", Texture.class);
        manager.load("bear_inactive.png", Texture.class);
        manager.load("bear_P1.png", Texture.class);
        manager.load("bear_P2.png", Texture.class);
        manager.load("panda_inactive.png", Texture.class);
        manager.load("panda_P1.png", Texture.class);
        manager.load("panda_P2.png", Texture.class);
        manager.load("Play_active_stroke.png", Texture.class);
        manager.load("Play_inactive_stroke.png", Texture.class);

        //InstructionScreen
        manager.load("instruction1.png", Texture.class);
        manager.load("instruction2.png", Texture.class);
        manager.load("instruction3.png", Texture.class);
        manager.load("instruction4.png", Texture.class);
        manager.load("Previous_active.png", Texture.class);
        manager.load("Previous_inactive.png", Texture.class);
        manager.load("Next_active.png", Texture.class);
        manager.load("Next_inactive.png", Texture.class);
        manager.load("Home_active.png", Texture.class);
        manager.load("Home_inactive.png", Texture.class);

        //EndScreen
        manager.load("endscreen_player1win.png", Texture.class);
        manager.load("endscreen_player2win.png", Texture.class);
        manager.load("Retry_active.png", Texture.class);
        manager.load("Retry_inactive.png", Texture.class);
        
        manager.finishLoading();
        return manager;
    }
}
