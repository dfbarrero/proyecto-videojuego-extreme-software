/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Characters;

import Map.Hitbox;
import Map.Mapa;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Javier Muñoz
 */
public class NPC extends Character
{
    private String name;
    private Inventory inventory;
    private final Hitbox collisionBox;
    private float speed;
    private Mapa map;
    
    public NPC(Image sprite, String id, int xPos, int yPos, String name, SpriteSheet img, float speed, int health, Mapa map)
    {
        super(sprite, id, xPos, yPos, speed, health);
        this.name = name;
        this.inventory = new Inventory();
        this.collisionBox = new Hitbox(xPos, yPos, 30, 30);
    }
    
    @Override
    public String toString()
    {
        return "xPos: "+getxPos()
              +"\nyPos: "+getyPos()
              +"\nID: "+getId()
              +"\nName: "+getName()
              +"\nSpeed: "+getSpeed()
              +"\nHealth: "+getHp();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
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
    public String getId()
    {
        return id;
    }
    
    @Override
    public void setId(String id)
    {
        this.id = id;
    }
    
}
