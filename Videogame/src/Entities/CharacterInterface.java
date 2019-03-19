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
public interface CharacterInterface {
    
    public String getID();
    
    public void setID(String id);
    
    public void move(int x, int y);
    
    public int getYPos();
    
    public int getXPos();
    
    public String toString();
}
