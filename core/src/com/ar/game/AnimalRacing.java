package com.ar.game;

import com.ar.game.handler.MyInputProcessor;
import com.ar.game.system.Systems;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.inject.*;

public class AnimalRacing extends ApplicationAdapter {
	SpriteBatch batch;
	public static Texture img;
	public static String TITLE = "Animal Racing";
	public static int V_WIDTH = 320;
	public static int V_HEIGHT = 240;
	public static int SCALE = 2;
	Engine engine = new Engine();
	private Injector injector;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		injector = Guice.createInjector(new GameModule(this));
		injector.getInstance(Systems.class).list.stream()
                .map(systemClass -> injector.getInstance(systemClass))
                .forEach(entitySystem -> engine.addSystem(entitySystem));
		Gdx.input.setInputProcessor(injector.getInstance(MyInputProcessor.class));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		engine.update(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
