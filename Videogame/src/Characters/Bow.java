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
public class Box extends Weapon {
    int arrows
    public Arco(int damage, String id, int arrows)
    {
        super(damage, id);
        this.arrows=arrows;
    }
    @Override
    public void use()
    {
        arrows--;
    }
    @Override
    public boolean available()
    {
        if(arrows>0) return true;
        else return false;
    }
    public int getArrows() {
        return arrows;
    }
    public void setArrows(int arrows) {
        this.arrows = arrows;
    }
}
