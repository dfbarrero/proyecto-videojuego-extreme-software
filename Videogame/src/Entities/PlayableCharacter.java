/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Javier Muñoz
 */
public class PlayableCharacter extends Character
{
    private String pCName;      //Name of the Playable Character
    private Inventory inventory;   //This array will serve as the inventory
    private int health;
    
    public PlayableCharacter(String id, int xPos, int yPos, String pCName)
    {
        super(id, xPos, yPos);
        this.pCName = pCName;
        this.inventory = new Inventory();
        this.health = 100;
    }
    
    @Override
    public String toString()    //This will return the player in a String format
    {
        return "xPos: "+xPos
              +"\nyPos: "+yPos
              +"\nID: "+id
              +"\nName: "+pCName;
    }

    public String getID()
    {
        return id;
    }
    
    public boolean isDead()
    {
        return health <=0;
    }
}
