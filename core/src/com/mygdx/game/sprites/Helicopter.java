package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by MoeRoddy on 12/7/16.
 */

public class Helicopter {
    private static final int GRAVITY = -10;
    public static int MOVEMENT = 100; // give the bird a velocity to the right
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Animation birdAnimation;
    private Texture texture;

    public Helicopter(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(10, 0, 0);
        texture = new Texture("birdanimation1.png");
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        bounds = new Rectangle(position.x, position.y, texture.getWidth() / 3, texture.getHeight()-6);
    }

    public void update(float dt) {
        birdAnimation.update(dt);
        velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);
        velocity.scl(1 / dt);
        if (position.y < 0) {
            position.y = 0;
            velocity.y = 0;
        }
        bounds.setPosition(position.x, position.y);
    }

    public TextureRegion getBird() {

        return birdAnimation.getFrame();
    }

    public Vector3 getPosition() {

        return position;
    }

    public void jump() {
        velocity.y = 150;
    }
    public Rectangle getBounds(){ return bounds; }
    public void dispose(){
        texture.dispose();
    }

    public int bird_Hieght(){
        return texture.getHeight();
    }
    public int helicopter_width(){
        return texture.getWidth();
    }

}


