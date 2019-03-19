/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entorno;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author jgome
 */
public class Additionals {
    private int PosX;
    private int PosY;
    private Image additional;
    public Additionals(int x, int y, String route) throws SlickException
    {
        this.PosX=x;
        this.PosY=y;
        this.additional=new Image(route);     
    }
    public void setPosX(int PosX) {
        this.PosX = PosX;
    }
    public void setPosY(int PosY) {
        this.PosY = PosY;
    }
    public void setBackground(Image background) {
        this.additional = background;
    }
    public int getPosX() {
        return PosX;
    }
    public int getPosY() {
        return PosY;
    }
    public Image getBackground() {
        return additional;
    }
}
