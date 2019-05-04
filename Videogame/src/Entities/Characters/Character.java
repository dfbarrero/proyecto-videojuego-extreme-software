/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Characters;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Javier Muñoz
 */

/*
*Characters can be "npc", "pc" or "enemy" depending on their ID and work differently
*depending on it, so make sure to assign the correct ID to every character.
*/
public abstract class Character implements CharacterInterface{
    protected String id;
    protected float xPos;
    protected float yPos;
    protected Image sprite;
    protected float speed;
    protected int hp;
    
    public Character(Image sprite, String id, float xPos, float yPos, float speed, int hp)
    {
        this.id = id;
        this.xPos = xPos;
        this.yPos = yPos;
        this.hp = hp;
        this.speed = speed;
        this.sprite = sprite;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the xPos
     */
    public float getxPos() {
        return xPos;
    }

    /**
     * @param xPos the xPos to set
     */
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    /**
     * @return the yPos
     */
    public float getyPos() {
        return yPos;
    }

    /**
     * @param yPos the yPos to set
     */
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    /**
     * @return the sprite
     */
    public Image getSprite() {
        return sprite;
    }

    /**
     * @param sprite the sprite to set
     */
    public void setSprite(SpriteSheet sprite) {
        this.sprite = sprite;
    }

    /**
     * @return the speed
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * @return the hp
     */
    public int getHp() {
        return hp;
    }

    /**
     * @param hp the hp to set
     */
    public void setHp(int hp) {
        this.hp = hp;
    }
    
    public abstract String toString();
    
}