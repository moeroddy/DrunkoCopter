package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by MoeRoddy on 12/12/16.
 */

public class Animation {
    // array to hold the animated sprite
    private Array<TextureRegion> frames;
    // how long a frame should stay in view before we switch to the next one
    private float maxFrameTime;
    // the time the frame should be present for how long
    private float currentFrameTime;
    private int frameCount;
    private int frame;

    // constructor
    // region = all the frames combined in one image
    // frameCount = how many frames we have
    // cycleTime: how long its gonna take to cycle thru the whole animation
    public Animation(TextureRegion region, int frameCount, float cycleTime) {

        frames=new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / (frameCount);

        for(int i = 0; i < frameCount; i++){
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }

        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;  // how long its gonna take divided by number of frames
        frame = 0; // this is the frame we're currently on
    }

    public void update(float dt){
        currentFrameTime += dt;

        if(currentFrameTime > maxFrameTime){
            frame++; // switch to the next frame
            currentFrameTime = 0; // reset the frame time
        }
        if(frame >= frameCount)
            frame = 0;
    }

    public TextureRegion getFrame(){
        return frames.get(frame);
    }
}
