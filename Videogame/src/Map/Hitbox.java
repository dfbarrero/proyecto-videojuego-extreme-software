/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;
import org.newdawn.slick.geom.Rectangle;

public class Hitbox {

    private Rectangle hitbox;
    private float posx, posy;
    public Hitbox(float x, float y, float width, float height) {
        posx=x; posy=y;
        hitbox = new Rectangle(x, y, width, height);
    }

    public Rectangle getRectangulo() {
        return hitbox;
    }

    public void setRectangulo(Rectangle rectangulo) {
        this.hitbox = rectangulo;
    }

    public void updatePos(float x, float y) {
        hitbox.setY(posx + y);
        hitbox.setX(posy + x);
    }

    public float getPosx() {
        return posx;
    }

    public void setPosx(float posx) {
        this.posx = posx;
    }

    public float getPosy() {
        return posy;
    }

    public void setPosy(float posy) {
        this.posy = posy;
    }
    
}