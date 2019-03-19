/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Items;

/**
 *
 * @author Javier Muñoz
 */
public abstract class Weapon extends Item
{
    protected int damage;
    
    public Weapon(String id, int damage)
    {
        super(id);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
    
    public abstract void use();
    
    public abstract boolean available();
    
}
