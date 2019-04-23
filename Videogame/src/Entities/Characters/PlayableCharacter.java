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
public class PlayableCharacter extends Character
{
    //Interaction with NPCs and enemies need to be implemented.
    private String pCName;
    private Inventory inventory;
    private SpriteSheet img;
    private final Rectangle collUp;      //These are the rectangles that represent
    private final Rectangle collDown;    //the collision box of the character
    private final Rectangle collLeft;
    private final Rectangle collRight;
    private float speed;
    
    public PlayableCharacter(String id, float xPos, float yPos, String pCName, SpriteSheet image, float speed, int health)
    {
        super(id, xPos, yPos, image, speed, health);
        this.pCName = pCName;
        this.inventory = new Inventory();
        this.collUp = new Rectangle(xPos+1, yPos+20, 10, 1);    //This is if the character
        this.collDown = new Rectangle(xPos+1, yPos, 10, 1);     //is 20 pixels tall
        this.collLeft = new Rectangle(xPos, yPos, 1, 10);       //by 10 pixels wide
        this.collRight = new Rectangle(xPos+10, yPos, 1, 10);
    }
    
    @Override
    public String toString()    //This will return the player in a String format
    {
        return "xPos: "+getxPos()
              +"\nyPos: "+getyPos()
              +"\nID: "+getId()
              +"\nName: "+pCName;
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
