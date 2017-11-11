package com.ar.game;

import com.ar.game.handler.KeyboardController;
import com.ar.game.screen.MainGameScreen;
import com.ar.game.system.Systems;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class AnimalRacing extends Game {
    public static String TITLE = "Animal Racing";
    public static int V_WIDTH = 640;
    public static int V_HEIGHT = 480;
    public static int SCALE = 2;
    public Injector injector;
    public SpriteBatch batch;
    public Engine engine = new Engine();
    public static KeyboardController controller = new KeyboardController();

	@Override
	public void create () {
	    batch = new SpriteBatch();
	    // Lines below need to run before MainScreen Starts
	    if (true) { // If main game is going to begin| Change a condition to what ever u want
            injector = Guice.createInjector(new GameModule(this));
            injector.getInstance(Systems.class).list.stream()
                    .map(systemClass -> injector.getInstance(systemClass))
                    .forEach(entitySystem -> engine.addSystem(entitySystem));
            this.setScreen(injector.getInstance(MainGameScreen.class));
        }
	}

	@Override
	public void render () {
	    super.render();
	}
	
	@Override
	public void dispose () {
	}
}
