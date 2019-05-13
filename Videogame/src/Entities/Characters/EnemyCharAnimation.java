/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Characters;


import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
/**
 *
 * @author Javier Muñoz
 */
public class EnemyCharAnimation
{
    private SpriteSheet enemyCharIdle;
    private Animation enemyCharIdleAnim;
    private SpriteSheet enemyCharIdleSmall;
    private Animation enemyCharIdleSmallAnim;
    private SpriteSheet enemyCharAttack;
    private Animation enemyCharAttackAnim;
    private int animationSpeed;
    private Image[] framesIdle = new Image[50];
    public EnemyCharAnimation() throws SlickException
    {
        animationSpeed = 300;
        enemyCharAttack = new SpriteSheet("PERSONAJES/BossFinal/attack/attackSpriteSheet50per.png",296,229);
        enemyCharIdle = new SpriteSheet("PERSONAJES/BossFinal/idle/IdleSpreadSheet2ndHalf50per.png",212,229);
        enemyCharAttackAnim = new Animation(enemyCharAttack, animationSpeed);
        enemyCharIdleAnim = new Animation(enemyCharIdle, animationSpeed);
        enemyCharIdleAnim.setPingPong(true);
    }

    /**
     * @return the enemyCharIdleAnim
     */
    public Animation getEnemyCharIdleAnim()
    {
        return enemyCharIdleAnim;
    }
    
    public Animation getEnemyCharAttackAnim()
    {
        return enemyCharAttackAnim;
    }
    
}