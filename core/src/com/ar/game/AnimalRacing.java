package com.ar.game;

import com.ar.game.handler.KeyboardController;
import com.ar.game.screen.MainMenuScreen;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AnimalRacing extends Game {
    public static String TITLE = "Animal Racing";
    public static int V_WIDTH = 640;
    public static int V_HEIGHT = 480;
    public static int SCALE = 2;

    public SpriteBatch batch;
    public Engine engine = new Engine();
    public static KeyboardController controller = new KeyboardController();

	@Override
	public void create () {
	    batch = new SpriteBatch();
        this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
	    super.render();
	}
	
	@Override
	public void dispose () {
	}
}
