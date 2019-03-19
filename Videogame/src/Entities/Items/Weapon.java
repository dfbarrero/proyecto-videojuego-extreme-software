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
<<<<<<< HEAD:Videogame/src/Characters/Weapon.java
public abstract class Weapon extends Item {
    private int damage;
    public abstract void use();
    public abstract boolean available();
=======
public abstract class Weapon extends Item
{
    protected int damage;
    
>>>>>>> 273b1846556215a9f0075e0f0751b3d0e5b18867:Videogame/src/Entities/Items/Weapon.java
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
