/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Characters;

/**
 *
 * @author Javier Muñoz
 */
public class PlayableCharacter extends Character
{
    private String pCName;//Name of the Playable Character
    public PlayableCharacter(String id, int xPos, int yPos, String pCName)
    {
        super(id, xPos, yPos);
        this.pCName = pCName;
    }
    
    @Override
    public String toString()
    {
        return "xPos: "+xPos
              +"\nyPos: "+yPos
              +"\nID: "+id
              +"\nName: "+pCName;
    }

    public String getID() {
        return id;
    }
}