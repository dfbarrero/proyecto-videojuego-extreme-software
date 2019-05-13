/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Items;

import java.io.Serializable;

/**
 *
 * @author jgome
 */
public class Sword extends Weapon implements Serializable
{
    private int durability;
    private String name;
    public Sword(String id, int damage, int durability, String name)
    {
        super("weapon", damage);
        this.durability = durability;
        this.name = name;
    }
    @Override
    public void use()
    {
        durability--;
    }
    @Override
    public boolean available()
    {
        return durability>0;
    }
    public void setDurability(int durability)
    {
        this.durability = durability;
    }
    public int getDurability()
    {
        return durability;
    }
    
    @Override
    public String toString()
    {
        return "Name: "+name
              +"\nDurability: "+durability
              +"\nDamage: "+damage;
    }

    @Override
    public String name() {
        return name;
    }
}
