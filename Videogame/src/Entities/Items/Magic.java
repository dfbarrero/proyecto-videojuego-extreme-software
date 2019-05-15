/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Items;


/**
 *
 * @author jgome
 */
public class Magic extends Weapon {
    private int magia;
    private String name;
    public Magic(String id, int damage, int durability, String name)
    {
        super(id, damage);
        this.magia=durability;
        this.name=name;
    }

    @Override
    public void use() {
        magia--;
    }

    @Override
    public boolean available() {
        return (magia>=0);
    }

    @Override
    public String toString() {
               return "Name: "+name
              +"\nDurability: "+magia
              +"\nDamage: "+damage;    
    }

    @Override
    public String name() {
        return name+" ("+magia+")";    
    }
    
}
