/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Characters;

import Map.Mapa;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import Map.*;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
/**
 *
 * @author Javier Muñoz
 */
public class PlayableCharacter extends Character
{
    //Interaction with NPCs and enemies need to be implemented.
    private int animationspeed;
    private String pCName;
    private Inventory inventory;
    private Inventory keys;
    private Hitbox collisionBox;   //This is the rectangles that represents
    private float speed;                    //the collision box of the char.
    private SpriteSheet mainCharLeft;
    private SpriteSheet mainCharRight;
    private SpriteSheet mainCharUp;
    private SpriteSheet mainCharIdle;
    private Animation mainCharLeftAnim;
    private Animation mainCharRightAnim;
    private Animation mainCharUpAnim;
    private Animation mainCharIdleAnim;
    public PlayableCharacter(Image sprite, String id, float xPos, float yPos, String pCName, float speed, int health) throws SlickException
    {
        
        super(sprite, id, xPos, yPos, speed, health);
        animationspeed=100;
        this.pCName = pCName;
        this.inventory = new Inventory();
        this.keys=new Inventory();
        this.collisionBox = new Hitbox(xPos, yPos, 30, 30);//This is if the character is 30x30 px
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
    }
    
    @Override
    public String toString()    //This will return the player in a String format
    {
        return "xPos: "+getxPos()
              +"\nyPos: "+getyPos()
              +"\nID: "+getId()
              +"\nName: "+getpCName()
              +"\nSpeed: "+getSpeed()
              +"\nHealth: "+getHp();
    }
    /**
     * 
     * @return if the character is dead.
     */
    public boolean isDead()
    {
        return this.getHp()<=0;
    }
    public void setAnimS(int speed)
    {
        this.animationspeed=speed;
    }
    /**
     * @return the pCName
     */
    public String getpCName() {
        return pCName;
    }

    /**
     * @param pCName the pCName to set
     */
    public void setpCName(String pCName) {
        this.pCName = pCName;
    }

    /**
     * @return the inventory
     */
    public Inventory getInventory() {
        return inventory;
    }
    public Inventory getKeys() {
        return keys;
    }
    /**
     * @param inventory the inventory to set
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * @return the collision box of the character
     */
    public Hitbox getCollision() {
        return collisionBox;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void move(float x, float y) {
        this.xPos = x;
        this.yPos = y;
    }

    @Override
    public float getYPos() {
        return yPos;
    }

    @Override
    public float getXPos() {
        return xPos;
    }
public SpriteSheet getMainCharLeft() {
        return mainCharLeft;
    }

    /**
     * @return the mainCharRight
     */
    public SpriteSheet getMainCharRight() {
        return mainCharRight;
    }

    /**
     * @return the mainCharUp
     */
    public SpriteSheet getMainCharUp() {
        return mainCharUp;
    }

    /**
     * @return the mainCharLeftAnim
     */
    public Animation getMainCharLeftAnim() {
        return mainCharLeftAnim;
    }

    /**
     * @return the mainCharRightAnim
     */
    public Animation getMainCharRightAnim() {
        return mainCharRightAnim;
    }

    /**
     * @return the mainCharUpAnim
     */
    public Animation getMainCharUpAnim() {
        return mainCharUpAnim;
    }

    /**
     * @return the mainCharIdle
     */
    public SpriteSheet getMainCharIdle() {
        return mainCharIdle;
    }

    /**
     * @return the mainCharIdleAnim
     */
    public Animation getMainCharIdleAnim() {
        return mainCharIdleAnim;
    }


    
}
