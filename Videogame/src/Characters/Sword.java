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
public class Sword extends Weapon
{
    int durability;
    public Sword(String id, int damage, int durability)
    {
        super(id, damage);
        this.durability=this.durability;
    }
    @Override
    public void use()
    {
        durability--;
    }
    @Override
    public boolean available()
    {
        if(durability>0) return true;
        else return false;
    }
    public void setDurability(int durability) {
        this.durability = durability;
    }
    public int getDurability() {
        return durability;
    }
}
