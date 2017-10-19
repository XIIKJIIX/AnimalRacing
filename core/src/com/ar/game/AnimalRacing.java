package com.ar.game;

import com.ar.game.component.PlayerComponent;
import com.ar.game.entity.Platform;
import com.ar.game.entity.Player;
import com.ar.game.handler.KeyboardController;
import com.ar.game.system.Systems;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import static com.badlogic.gdx.Input.Keys.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.google.inject.*;

public class AnimalRacing extends ApplicationAdapter {
	SpriteBatch batch;
	public static Texture img;
	public static String TITLE = "Animal Racing";
	public static int V_WIDTH = 1280;
	public static int V_HEIGHT = 720;
	public static int SCALE = 2;
	Engine engine = new Engine();
	public static KeyboardController controller = new KeyboardController();
	private Injector injector;

	private void createEntities() {
	    engine.addEntity(injector.getInstance(Platform.class));
	    engine.addEntity(new Player(
	            injector.getInstance(World.class),
                new PlayerComponent(LEFT, RIGHT, UP, SHIFT_RIGHT, 300F)
                ));
        engine.addEntity(new Player(
                injector.getInstance(World.class),
                new PlayerComponent(A, D, W, Q, 300F)
        ));
    }
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		injector = Guice.createInjector(new GameModule(this));
		injector.getInstance(Systems.class).list.stream()
                .map(systemClass -> injector.getInstance(systemClass))
                .forEach(entitySystem -> engine.addSystem(entitySystem));
		Gdx.input.setInputProcessor(controller);
        createEntities();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		engine.update(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
