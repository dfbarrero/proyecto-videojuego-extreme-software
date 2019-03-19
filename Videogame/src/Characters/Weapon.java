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
public abstract class Weapon extends Item {
    private int damage;
    public abstract void use();
    public abstract boolean available();
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
    
}
