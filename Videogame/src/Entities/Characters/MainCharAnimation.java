/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Characters;

import java.io.Serializable;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author jgome
 */
public class MainCharAnimation{
    private SpriteSheet mainCharLeft;
    private SpriteSheet mainCharRight;
    private SpriteSheet mainCharUp;
    private SpriteSheet mainCharIdle;
    private Animation mainCharLeftAnim;
    private Animation mainCharRightAnim;
    private Animation mainCharUpAnim;
    private Animation mainCharIdleAnim;
    private int animationspeed;
    public MainCharAnimation() throws SlickException
    {
        animationspeed=100;
        mainCharLeft = new SpriteSheet("src/Sprites/WalkLeft.png",27,30);
        mainCharRight = new SpriteSheet("src/Sprites/WalkRight.png",27,30);
        mainCharUp = new SpriteSheet("src/Sprites/WalkUp.png",27,30);
        mainCharIdle = new SpriteSheet("src/Sprites/Idle.png",27,30);
        mainCharLeftAnim = new Animation(mainCharLeft,animationspeed);
        mainCharLeftAnim.setPingPong(true);
        mainCharRightAnim = new Animation(mainCharRight,animationspeed);
        mainCharRightAnim.setPingPong(true);
        mainCharUpAnim = new Animation(mainCharUp,animationspeed);
        mainCharUpAnim.setPingPong(true);
        mainCharIdleAnim=new Animation(mainCharIdle, animationspeed);
    }

    public SpriteSheet getMainCharLeft() {
        return mainCharLeft;
    }

    public void setMainCharLeft(SpriteSheet mainCharLeft) {
        this.mainCharLeft = mainCharLeft;
    }

    public SpriteSheet getMainCharRight() {
        return mainCharRight;
    }

    public void setMainCharRight(SpriteSheet mainCharRight) {
        this.mainCharRight = mainCharRight;
    }

    public SpriteSheet getMainCharUp() {
        return mainCharUp;
    }

    public void setMainCharUp(SpriteSheet mainCharUp) {
        this.mainCharUp = mainCharUp;
    }

    public SpriteSheet getMainCharIdle() {
        return mainCharIdle;
    }

    public void setMainCharIdle(SpriteSheet mainCharIdle) {
        this.mainCharIdle = mainCharIdle;
    }

    public Animation getMainCharLeftAnim() {
        return mainCharLeftAnim;
    }

    public void setMainCharLeftAnim(Animation mainCharLeftAnim) {
        this.mainCharLeftAnim = mainCharLeftAnim;
    }

    public Animation getMainCharRightAnim() {
        return mainCharRightAnim;
    }

    public void setMainCharRightAnim(Animation mainCharRightAnim) {
        this.mainCharRightAnim = mainCharRightAnim;
    }

    public Animation getMainCharUpAnim() {
        return mainCharUpAnim;
    }

    public void setMainCharUpAnim(Animation mainCharUpAnim) {
        this.mainCharUpAnim = mainCharUpAnim;
    }

    public Animation getMainCharIdleAnim() {
        return mainCharIdleAnim;
    }

    public void setMainCharIdleAnim(Animation mainCharIdleAnim) {
        this.mainCharIdleAnim = mainCharIdleAnim;
    }

    public int getAnimationspeed() {
        return animationspeed;
    }

    public void setAnimationspeed(int animationspeed) {
        this.animationspeed = animationspeed;
    }
    
}
