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
public interface CharacterInterface {
    
    public String getId();
    
    public void setId(String id);
    
    public void move(float x, float y);
    
    public float getYPos();
    
    public float getXPos();
    
    public String toString();
}
