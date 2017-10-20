package com.ar.game;

import com.ar.game.handler.CollisionDetector;
import com.ar.game.handler.KeyboardController;
import com.ar.game.system.*;
import static com.ar.game.constant.B2Dvars.*;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import java.util.Arrays;

public class GameModule implements Module {
    private AnimalRacing animalRacing;

    GameModule(AnimalRacing animalRacing) {
        this.animalRacing = animalRacing;
    }

    @Override
    public void configure(Binder binder) {
        binder.requireAtInjectOnConstructors();
        binder.requireExactBindingAnnotations();
        binder.bind(SpriteBatch.class).toInstance(animalRacing.batch);
        binder.bind(KeyboardController.class).toInstance(animalRacing.controller);
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
                RenderingSystem.class,
                PlayerControlSystem.class,
                PhysicsDebugSystem.class,
                PhysicsSystem.class,
                PhysicsSynchronizationSystem.class
        ));
    }
}
