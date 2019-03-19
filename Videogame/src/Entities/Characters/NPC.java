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
public class NPC extends Character
{
    //NPC methods need to be implemented.
    private String name;
    
    public NPC(String id, int xPos, int yPos, String name)
    {
        super("pc", xPos, yPos);
        this.name = name;
    }
    
    @Override
    public String toString()
    {
        return "xPos: "+xPos
              +"\nyPos: "+yPos
              +"\nID: "+id
              +"\nName: "+getName();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    
}
