package com.ar.game;

import com.ar.game.system.*;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
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
        return new World(new Vector2(0F, -9.81F), true);
    }

    @Provides
    @Singleton
    public OrthographicCamera camera() {
        Integer viewPortWidth = Gdx.graphics.getWidth();
        Integer viewPortHeight = Gdx.graphics.getHeight();

        OrthographicCamera camera = new OrthographicCamera(viewPortWidth, viewPortHeight);
        camera.position.set(viewPortWidth / 2F, viewPortHeight / 2F, 0F);
        camera.update();
        return camera;
    }

    @Provides
    @Singleton
    public Systems systems() {
        return new Systems(Arrays.asList(
                RenderingSystem.class,
                PhysicsDebugSystem.class,
                PhysicsSystem.class,
                PhysicsSynchronizationSystem.class
        ));
    }
}
