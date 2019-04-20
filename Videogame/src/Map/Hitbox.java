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

    public float getPosx() {
        return posx;
    }

    public float getPosy() {
        return posy;
    }
    public Hitbox(float x, float y, float width, float height) {
        posx=x;
        posy=y;
        hitbox = new Rectangle(x, y, width, height);
    }

    public Rectangle getRectangulo() {
        return hitbox;
    }

    public void setRectangulo(Rectangle rectangulo) {
        this.hitbox = rectangulo;
    }

    public void updatePos(float x, float y) {
        hitbox.setY(hitbox.getY() + y);
        hitbox.setX(hitbox.getX() + x);
    }
}