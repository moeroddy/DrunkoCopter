package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.States.GameStateManager;
import com.mygdx.game.States.MenuState;

public class HeliDemo extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	public static final String TITLE = "Flappy Helicopter";
	private GameStateManager gsm;

	private SpriteBatch batch;

	private Music music;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch(); // Built in class to organize
		gsm = new GameStateManager(); // GameStateManager.java
		Gdx.gl.glClearColor(1, 0, 0, 1); // clear the screen
		gsm.push(new MenuState(gsm)); // add the menu state to the top of the array to display it first
		music = Gdx.audio.newMusic(Gdx.files.internal("music_loop.mp3"));
		music.setLooping(true);
		music.setVolume(0.8f);
		music.play();

	}

	@Override
	public void render () { // function to display things on the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    gsm.update(Gdx.graphics.getDeltaTime()); // get the time, and call function update on whatever state
		// that is at the top of the array
		gsm.render(batch); // call render function in GameStateManager.java--which takes sb spritebatch
		// and will get the state at the top of the array and call its own render function


	}
	
	@Override
	public void dispose () { // this function to free the memory
		batch.dispose();
		img.dispose();
	}
}
