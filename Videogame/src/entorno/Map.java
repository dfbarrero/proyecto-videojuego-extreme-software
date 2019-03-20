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
public class Map {
    private int PosX;
    private int PosY;
    private Image background;
    private int[] Limitx, Limity;
    public Map(int x, int y, String route) throws SlickException
    {
        Limitx=new int[2];
        Limity=new int[2];
        this.PosX=x;
        this.PosY=y;
        this.background=new Image(route);     
    }

    public int[] getLimitx() {
        return Limitx;
    }

    public void setLimitx(int[] Limitx) {
        this.Limitx = Limitx;
    }

    public int[] getLimity() {
        return Limity;
    }

    public void setLimity(int[] Limity) {
        this.Limity = Limity;
    }
    
    public void setPosX(int PosX) {
        this.PosX = PosX;
    }
    public void setPosY(int PosY) {
        this.PosY = PosY;
    }
    public void setBackground(Image background) {
        this.background = background;
    }
    public int getPosX() {
        return PosX;
    }
    public int getPosY() {
        return PosY;
    }
    public Image getBackground() {
        return background;
    }
}
