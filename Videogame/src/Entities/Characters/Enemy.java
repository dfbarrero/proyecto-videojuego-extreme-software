/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Characters;

/**
 *
 * @author Javier Muñoz
 */
public class Enemy extends Character {
    //Enemy interactions with the player yet to be implemented.
    private String name;
    private Inventory inventory;
    private int health;
    
    public Enemy(String id, int xPos, int yPos, String pCName)
    {
        super("enemy", xPos, yPos);
        this.name = pCName;
        this.inventory = new Inventory();
        this.health = 100;
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
