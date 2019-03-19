/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Characters;
/**
 *
 * @author jgome
 */
public class Bow extends Weapon
{
    private String name;
    private int arrows;
    public Bow(String id, int damage, int arrows, String name)
    {
        super("weapon", damage);
        this.arrows = arrows;
        this.name = name;
    }
    @Override
    public void use()
    {
        arrows--;
    }
    @Override
    public boolean available()
    {
        return arrows>0;
    }
    
    public int getArrows()
    {
        return arrows;
    }
    
    public void setArrows(int arrows)
    {
        this.arrows = arrows;
    }
    
    public String toString()
    {
        return "Name: "+name
              +"\nAmmo: "+arrows
              +"\nDamage: "+damage;
    }
}
