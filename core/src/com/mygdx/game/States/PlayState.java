package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.HeliDemo;
import com.mygdx.game.sprites.Helicopter;
import com.mygdx.game.sprites.Bomb;
import com.mygdx.game.sprites.Coin;

/**
 * Created by MoeRoddy on 12/7/16.
 */

public class PlayState extends State { // inherits from state
    private Helicopter helicopter; // create instance of class Helicopter --- Helicopter.java
    private Texture bg; // create a background texture
    private Texture ground; // create a ground texture
    private Vector2 groundPos1, groundPos2;
    private static final int BOMB_SPACING = 125;
    private static final int BOMB_COUNT = 4;
    private static final int Coin_Count = 2;
    private Array<Bomb> tubes; // create stack array of objects from CLASS TUBE
    private Array<Coin> coins; // create a sack array that has coins in it
    public static int GROUND_Y_OFFSET = -70; // the height of the ground
    public static int score;
    private static int bomb_WID = 30;
    private static int bomb_HI = 30;





    BitmapFont font = new BitmapFont(); //or use alex answer to use custom font

    public PlayState(GameStateManager gsm) {
        super(gsm); // pass by refrence (copy methods in that class)
        score = 0;
        cam.setToOrtho(false, HeliDemo.WIDTH / 2, HeliDemo.HEIGHT / 2); // how much from the game world we wanna
                                                                            // display to the screen
        helicopter = new Helicopter(50,300); // start location for the helicopter
        bg = new Texture("sahara.jpg"); // background image
        ground = new Texture("ground.png");


        tubes = new Array<Bomb>(); // assign the stack of Tubes in variable names tubes
        for(int i=1; i<=BOMB_COUNT; i++){ // for loop to iterate through the stack array - TUBE_COUNT = 4
            tubes.add(new Bomb(i * (BOMB_SPACING + Bomb.TUBE_WIDTH))); // add new object of class Bomb to the array
        }

        coins = new Array<Coin>();
        for(int i=1; i<Coin_Count; i++){
            coins.add(new Coin(i * (BOMB_SPACING + Coin.coin_width)));
        }



    }

    @Override
    protected void handleInput() {
        updateGround();

        if(Gdx.input.isTouched()) // if click mouse or touch the screen then jump
            helicopter.jump(); // call jump function

    }

    @Override
    public void update(float dt) {
        handleInput();
        helicopter.update(dt); // call update function from Helicopter.java
        cam.position.x = helicopter.getPosition().x + 80; // reposition the camera 80 pixels to the right of the helicopter

        for(int i = 0; i<tubes.size; i++){
            Bomb bomb = tubes.get(i);
            // remove bombs past the screen and to score
            if(cam.position.x - (cam.viewportWidth / 2) > bomb.getPosTopTube().x + bomb.getTopTube().getWidth()){
                bomb.reposition1(bomb.getPosTopTube().x + (bomb.TUBE_WIDTH + BOMB_SPACING) * BOMB_COUNT);
                score += 5;
            }

            if(cam.position.x - (cam.viewportWidth / 2) > bomb.getPosBotTube().x + bomb.getBottomTube().getWidth()) {
                bomb.reposition2(bomb.getPosBotTube().x + (bomb.TUBE_WIDTH + BOMB_SPACING) * BOMB_COUNT);
                score += 5;

            }
            // check for collision between bombs and player
            if (bomb.collides(helicopter.getBounds())) {
                gsm.set(new GameOverState(gsm));
               // score = 0;

            }
        }

        for(int i = 0; i<coins.size; i++) {
            Coin coin = coins.get(i);
            // reposition gas tanks beyond the screen and add score
            if (cam.position.x - (cam.viewportWidth / 2) > coin.getPosCoin().x + coin.getCoin().getWidth()) {
                coin.reposition(coin.getPosCoin().x + (coin.coin_width + BOMB_SPACING) * BOMB_COUNT);
                score += 5; // score will add if we didnt catch the tank
            }
            // check for collision
            if(coin.collides(helicopter.getBounds())){
                score +=50;
                coin.reposition(coin.getPosCoin().x + (coin.coin_width + BOMB_SPACING) * BOMB_COUNT);
            }
        }

        // if helicopter hits the ground
        if(helicopter.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET) {
            gsm.set(new GameOverState(gsm));
           // score = 0;

        }
        // if helicopter hits the upper border
        if(helicopter.getPosition().y >= cam.viewportHeight - helicopter.bird_Hieght() + 7) {
            gsm.set(new GameOverState(gsm));
           // score = 0;

        }



        cam.update(); // update the position of the camera


    }

    @Override
    public void render(SpriteBatch sb) {

        groundPos1 = new Vector2(cam.position.x - (cam.viewportWidth / 2), GROUND_Y_OFFSET);
        groundPos2 = new Vector2(cam.position.x - (cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);

        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        // draw the camera
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0); // draw cam position
        // draw the helicopter
        sb.draw(helicopter.getBird(), helicopter.getPosition().x, helicopter.getPosition().y);

        // draw the bombs
        for(Bomb bomb : tubes) { // for loop to draw the tubes in the stack array
            sb.draw(bomb.getTopTube(), bomb.getPosTopTube().x, bomb.getPosTopTube().y, bomb_WID, bomb_HI);
            sb.draw(bomb.getBottomTube(), bomb.getPosBotTube().x, bomb.getPosBotTube().y, bomb_WID, bomb_HI);
        }

        // draw the coins or gas tanks
        for(Coin coin: coins) { // for loop to draw the tubes in the stack array
            sb.draw(coin.getCoin(), coin.getPosCoin().x, coin.getPosCoin().y);
        }

        // draw the ground
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        //draw the score on the game

        //draw the score
        font.draw(sb, "Score: " + score , 10 + cam.position.x - (cam.viewportWidth/2), 400);


        changediff(sb);


        sb.end();

    }

    public void changediff(SpriteBatch sb){ // change diffuculty depending on score leve;

        if (score > 100 && score < 400){
            GROUND_Y_OFFSET = -70; // change the level of the ground
            Helicopter.MOVEMENT = 120;
        }


        else if (score > 400 && score < 10000){
            GROUND_Y_OFFSET = -55;
            bomb_HI = 40;
            bomb_WID = 40;
            helicopter.MOVEMENT = 130;
        }

        else if (score > 1000 && score < 15000){
            GROUND_Y_OFFSET = -45;
            bomb_HI = 45;
            bomb_WID = 45;
            helicopter.MOVEMENT = 140;
        }

        else if (score > 1500){
            GROUND_Y_OFFSET = -30;
            bomb_HI = 50;
            bomb_WID = 50;
            helicopter.MOVEMENT = 160;
        }

        else{
            helicopter.MOVEMENT = 100;
            bomb_WID = 30;
            bomb_HI = 30;
        }

    }

    @Override
    public void dispose() {
        bg.dispose();
        helicopter.dispose();
        ground.dispose();
        for(int i = 0; i<tubes.size; i++){
            Bomb bomb = tubes.get(i);
            bomb.dispose();
        }
        System.out.println("PlayState disposed");

    }

    private void updateGround(){
        if(cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth()){
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if(cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth()){
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }

}
