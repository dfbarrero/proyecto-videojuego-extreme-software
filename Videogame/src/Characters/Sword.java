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
public class Sword extends Weapon{
    int endurance;
    public Sword(String id, int damage, int durability)
    {
        super(id, damage);
        this.endurance=endurance;
    }
    @Override
    public void use()
    {
        endurance--;
    }
    @Override
    public boolean available()
    {
        if(endurance>0) return true;
        else return false;
    }
    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }
    public int getEndurance() {
        return endurance;
    }
}
