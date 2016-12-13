package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.States.PlayState;

import java.util.Random;

/**
 * Created by MoeRoddy on 12/12/16.
 */

public class Coin {
    private Texture coin;
    private Vector2 posCoin;
    private Random rand;
    private Rectangle boundCoin;
    private static int FLUC = 100;
    public static float coin_width = 30;


    public Coin(float x){
        coin = new Texture("Gas_Tank.png");
        rand = new Random();

        posCoin = new Vector2(x, rand.nextInt(FLUC) - PlayState.GROUND_Y_OFFSET);
        boundCoin = new Rectangle(posCoin.x, posCoin.y, coin.getWidth() - 5, coin.getHeight() - 5);

    }

    public Texture getCoin() {
        return coin;
    }

    public Vector2 getPosCoin() {
        return posCoin;
    }


    public void reposition(float x){
        posCoin = new Vector2(x, rand.nextInt(FLUC) - PlayState.GROUND_Y_OFFSET);
        boundCoin.setPosition(posCoin.x, posCoin.y);
    }

    public boolean collides(Rectangle player){

        return player.overlaps(boundCoin);

    }


}
