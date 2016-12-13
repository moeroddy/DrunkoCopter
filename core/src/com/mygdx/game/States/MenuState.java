package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.HeliDemo;

/**
 * Created by MoeRoddy on 12/7/16.
 */

public class MenuState extends State { // inherits from state.java
    private Texture background; // Background of the game
    //private Texture playBtn; // Play button
    private Texture text;



    public MenuState(GameStateManager gsm){
        super(gsm); // pass by refrence functions in GameStateManager.java
        cam.setToOrtho(false, HeliDemo.WIDTH, HeliDemo.HEIGHT);
        background = new Texture("sahara.jpg"); // background image
        //playBtn = new Texture(("playbtn.png")); // the play button
        text = new Texture("text.png"); // background image



    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){ // if we click mouse or touch the screen
            gsm.set(new PlayState(gsm)); // delete Previous state from stack array and add PlayState state
        }

    }

    @Override
    public void update(float dt) {
        handleInput(); // call handleInput() -- look up
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin(); // built in method in SpriteBatch class
        sb.draw(background, 0, 0, HeliDemo.WIDTH, HeliDemo.HEIGHT); // (image, x, y, width, height) location and size of image
        //sb.draw(playBtn, (HeliDemo.WIDTH / 2) - (playBtn.getWidth() / 2), HeliDemo.HEIGHT / 3 );
        // (image, x, y)
        sb.draw(text, 40, HeliDemo.HEIGHT / 2);
        sb.end(); // built in method to stop rendering to the screen

    }

    public void dispose(){ // FREEEEEEE MMMMEEEEMMMOOORRRYYYY
        background.dispose();
        //playBtn.dispose();
        System.out.println("Menu State Disposed");
    }
}
