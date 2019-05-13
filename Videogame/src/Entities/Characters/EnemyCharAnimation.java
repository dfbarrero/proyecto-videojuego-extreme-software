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
    private SpriteSheet enemyCharDying;
    private Animation enemyCharDyingAnim;
    private int animationSpeed;
    private Image[] framesIdle = new Image[50];
    public EnemyCharAnimation() throws SlickException
    {
        animationSpeed = 300;
        enemyCharIdle = new SpriteSheet("PERSONAJES/BossFinal/idle/IdleSpreadSheet2ndHalf.png",424,457);
        enemyCharIdleAnim = new Animation(enemyCharIdle, animationSpeed);
    }

    /**
     * @return the enemyCharIdleAnim
     */
    public Animation getEnemyCharIdleAnim() {
        return enemyCharIdleAnim;
    }
    
}