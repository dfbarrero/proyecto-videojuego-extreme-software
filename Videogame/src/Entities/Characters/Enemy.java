/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Characters;

import Map.Hitbox;
import Map.Mapa;

/**
 *
 * @author Javier Muñoz
 */
public class Enemy extends Character {
    //Enemy interactions with the player yet to be implemented.
    private String name;
    private Inventory inventory;
    private final Hitbox collisionBox;
    private float speed;
    private Mapa map;
    
    
    public Enemy(String id, float xPos, float yPos, String name, float speed, int health)
    {
        super(id, xPos, yPos, speed, health);
        this.name = name;
        this.inventory = new Inventory();
        this.collisionBox = new Hitbox(xPos, yPos, 30, 30);//Enemy with a coll. box of 30px squared
    }
    
    @Override
    public String toString()    //This will return the enemy in a String format
    {
        return "xPos: "+getxPos()
              +"\nyPos: "+getyPos()
              +"\nID: "+getId()
              +"\nName: "+getName()
              +"\nSpeed: "+getSpeed()
              +"\nHealth: "+getHp();
    }
    public void atacar(PlayableCharacter enemigo)
    {
        int hpr, daño = 0;
        if(!isDead()||!enemigo.isDead())
        {
            daño=(int)(15+Math.random()*5);
            hpr=enemigo.getHp()-daño;
            enemigo.setHp(hpr);
        }
        System.out.println("daño afligido="+daño+" || hp restante="+hp);
    }
    @Override
    public String getId()
    {
        return id;
    }
    
    public boolean isDead()
    {
        return getHp()<=0;
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
    
    @Override
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the collisionBox
     */
    public Hitbox getCollisionBox() {
        return collisionBox;
    }

    /**
     * @return the map
     */
    public Mapa getMap() {
        return map;
    }

    /**
     * @param map the map to set
     */
    public void setMap(Mapa map) {
        this.map = map;
    }
}
