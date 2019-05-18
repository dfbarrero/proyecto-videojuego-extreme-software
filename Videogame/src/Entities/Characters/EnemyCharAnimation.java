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
    private int animationSpeed, speedAtack;
    private Image[] framesIdle = new Image[50];
    public EnemyCharAnimation() throws SlickException
    {
        animationSpeed = 300;
        speedAtack=290;
        enemyCharIdleSmall = new SpriteSheet("src/BossIdle/IdleSpreadSheetSmall.png", 54, 59);
        enemyCharAttack = new SpriteSheet("src/BossIdle/attackSpriteSheet50per.png",296,229);
        enemyCharIdle = new SpriteSheet("src/BossIdle/IdleSpreadSheet2ndHalf50per.png",212,229);
        enemyCharAttackAnim = new Animation(enemyCharAttack, speedAtack);
        enemyCharIdleAnim = new Animation(enemyCharIdle, animationSpeed);
        enemyCharIdleSmallAnim = new Animation(enemyCharIdleSmall, animationSpeed);
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
    
    public Animation getEnemyCharIdleSmallAnim()
    {
        return enemyCharIdleSmallAnim;
    }
    
}