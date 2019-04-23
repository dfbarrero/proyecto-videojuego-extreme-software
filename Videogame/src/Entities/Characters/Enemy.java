/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Characters;

import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Javier Muñoz
 */
public class Enemy extends Character {
    //Enemy interactions with the player yet to be implemented.
    private String name;
    private Inventory inventory;
    private final Rectangle collisionBox;
    private float speed;
    private Mapa map;
    
    
    public Enemy(String id, float xPos, float yPos, String pCName, SpriteSheet img, float speed, int health, Mapa map)
    {
        super("enemy", xPos, yPos, img, speed, health);
        this.name = pCName;
        this.inventory = new Inventory();
        this.collisionBox = new Rectangle(xPos, yPos, 30, 30);
    }
    
    @Override
    public String toString()    //This will return the player in a String format
    {
        return "xPos: "+getxPos()
              +"\nyPos: "+getyPos()
              +"\nID: "+getId()
              +"\nName: "+name;
    }

    public String getID()
    {
        return getId();
    }
    
    public boolean isDead()
    {
        return health <=0;
    }
}
