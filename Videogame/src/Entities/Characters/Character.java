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
public abstract class Character implements CharacterInterface{
    protected String id;
    protected int xPos;
    protected int yPos;
    
    public Character(String id, int xPos, int yPos)
    {
        this.id = id;
        this.xPos = xPos;
        this.yPos = yPos;
    }
    
    public void move(int x, int y)
    {
        xPos = x;
        yPos = y;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }
    
    public abstract String toString();
}
