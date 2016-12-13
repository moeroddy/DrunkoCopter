package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.HeliDemo;

/**
 * Created by MoeRoddy on 12/12/16.
 */

public class GameOverState extends State {
    BitmapFont font = new BitmapFont(); //or use alex answer to use custom font
    private Texture background;
    private Texture gameover;
    private Texture reload;

    public GameOverState(GameStateManager gsm){
        super(gsm);
        cam.setToOrtho(false, HeliDemo.WIDTH, HeliDemo.HEIGHT);
        background = new Texture("sahara.jpg"); // background image
        gameover = new Texture("gameover.png");
        reload = new Texture("reload.png");

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){ // if we click mouse or touch the screen
            gsm.set(new PlayState(gsm)); // delete Previous state from stack array and add PlayState state
        }
    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin(); // built in method in SpriteBatch class
        sb.draw(background, 0, 0, HeliDemo.WIDTH, HeliDemo.HEIGHT); // (image, x, y, width, height) location and size of image
        sb.draw(gameover, -20, 600);
        sb.draw(reload, HeliDemo.WIDTH/2 - reload.getWidth()/2, 400);
        font.draw(sb, "Your Score is : " + PlayState.score , 10 + cam.position.x - (cam.viewportWidth/2), 400);

        sb.end(); // built in method to stop rendering to the screen
    }

    @Override
    public void dispose() {
        background.dispose();
        gameover.dispose();
    }


}
