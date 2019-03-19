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
public class PlayableCharacter extends Character
{
    //Interaction with NPCs and enemies need to be implemented.
    private String pCName;
    private Inventory inventory;
    private int health;
    
    public PlayableCharacter(String id, int xPos, int yPos, String pCName)
    {
        super("pc", xPos, yPos);
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
