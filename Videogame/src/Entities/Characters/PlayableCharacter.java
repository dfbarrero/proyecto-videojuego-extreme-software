/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Characters;

import Entities.Items.Weapon;
import Map.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import org.newdawn.slick.SlickException;
/**
 *
 * @author Javier Muñoz
 */
public class PlayableCharacter extends Character implements Serializable
{
    private boolean dead;
    private String pCName;
    private Inventory inventory;
    private Inventory keys;
    private Hitbox collisionBox;   //This is the rectangles that represents
    private float speed;                    //the collision box of the char.
    
    public PlayableCharacter(String id, float xPos, float yPos, String pCName, float speed, int health) throws SlickException, FileNotFoundException, IOException
    {
        super(id, xPos, yPos, speed, health);
        this.dead = false;
        this.pCName = pCName;
        this.inventory = new Inventory();
        this.keys=new Inventory();
        this.collisionBox = new Hitbox(xPos, yPos+1, 29, 26);//This is if the character is 30x30 px
        this.speed=speed;
    }

    public Hitbox getCollisionBox() {
        return collisionBox;
    }

    public void setCollisionBox(Hitbox collisionBox) {
        this.collisionBox = collisionBox;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(float speed) {
        this.speed = speed;
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
     * @param arma that is going to be used
     * @param enemigo that is going to be attacked
     */
    public void atacar(Weapon arma, Enemy enemigo)
    {
        int hpr, daño = 0;
        if(arma.available() && !isDead())
        {
            daño=(int)(arma.getDamage()+Math.random()*10);
            hpr=enemigo.getHp()-daño;
            enemigo.setHp(hpr);
            arma.use();
        }
        System.out.println("daño afligido="+daño+" || hp restante="+hp);
    }
    public boolean isDead()
    {
        dead = hp == 0;
        return dead;
    }
    
    public void setDead(boolean value)
    {
        this.dead = value;
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
    
}
