/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Characters;

import Map.*;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
/**
 *
 * @author Javier Muñoz
 */
public class PlayableCharacter extends Character
{
    //Interaction with NPCs and enemies need to be implemented.
    private String pCName;
    private Inventory inventory;
    private Hitbox collisionBox;   //This is the rectangles that represents
    private float speed;           //the collision box of the char.
    private SpriteSheet mainCharLeft;
    private SpriteSheet mainCharIdle;
    private SpriteSheet mainCharRight;
    private SpriteSheet mainCharUp;
    private Animation mainCharIdleAnim;
    private Animation mainCharLeftAnim;
    private Animation mainCharRightAnim;
    private Animation mainCharUpAnim;
    
    public PlayableCharacter(String id, float xPos, float yPos, String pCName, float speed, int health) throws SlickException
    {
        super(id, xPos, yPos, speed, health);
        this.pCName = pCName;
        this.inventory = new Inventory();
        this.collisionBox = new Hitbox(xPos, yPos, 20, 20);//This is if the character is 30x30 px
        mainCharLeft = new SpriteSheet("src/Sprites/WalkLeft.png",30,30);
        mainCharRight = new SpriteSheet("src/Sprites/WalkRight.png",30,30);
        mainCharUp = new SpriteSheet("src/Sprites/WalkUp.png",30,30);
        mainCharIdle = new SpriteSheet("src/Sprites/Idle.png",30,30);
        mainCharLeftAnim = new Animation(mainCharLeft,200);
        mainCharRightAnim = new Animation(mainCharRight,200);
        mainCharUpAnim = new Animation(mainCharUp,200);
        mainCharIdleAnim = new Animation(mainCharIdle,200);
        
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
     * @return if the character is dead.
     */
    public boolean isDead()
    {
        return this.getHp()<=0;
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

    /**
     * @param inventory the inventory to set
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * @return the collision box of the character
     */
    public Hitbox getCollisionBox() {
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

    /**
     * @return the speed
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * @return the mainCharLeft
     */
    public SpriteSheet getMainCharLeft() {
        return mainCharLeft;
    }

    /**
     * @return the mainCharIdle
     */
    public SpriteSheet getMainCharIdle() {
        return mainCharIdle;
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
     * @return the mainCharIdleAnim
     */
    public Animation getMainCharIdleAnim() {
        return mainCharIdleAnim;
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

}
