package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

/**
 * Created by MoeRoddy on 12/9/16.
 */

public class Bomb {
    private Texture topTube;
    private Texture bottomTube;
    private Vector2 posTopTube;
    private Vector2 posBotTube;
    private Random rand;
    private static final int FLUC = 130;
    private static final int TUBE_GAP = 100;
    private static final int LOWEST_OPENING = 120;
    public static final int TUBE_WIDTH = 52;
    private Rectangle boundsTop;
    private Rectangle boundsBot;

    public Bomb(float x){
        topTube = new Texture("Bomb.png");
        bottomTube = new Texture("bomb.png");
        rand = new Random();

        posTopTube = new Vector2(x, rand.nextInt(FLUC) + LOWEST_OPENING);
        posBotTube = new Vector2(rand.nextInt(FLUC) + x, posTopTube.y  - bottomTube.getHeight());
        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth() - 10, topTube.getHeight() - 10);
        boundsBot = new Rectangle(posBotTube.x, posBotTube.y, bottomTube.getWidth() - 10, bottomTube.getHeight() - 10);

    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    public void reposition1(float x){
        posTopTube.set(x, rand.nextInt(FLUC) + LOWEST_OPENING);
        boundsTop.setPosition(posTopTube.x, posTopTube.y);
    }

    public void reposition2(float x){
        posBotTube.set(rand.nextInt(FLUC) + x, posTopTube.y - bottomTube.getHeight());
        boundsBot.setPosition(posBotTube.x, posBotTube.y);

    }

    public boolean collides(Rectangle player){

        return player.overlaps(boundsTop) || player.overlaps(boundsBot);

    }

    public void dispose(){
        topTube.dispose();
        bottomTube.dispose();
    }
}
