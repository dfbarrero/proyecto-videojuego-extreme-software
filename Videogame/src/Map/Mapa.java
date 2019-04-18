/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Play;

/**
 *
 * @author jgome
 */

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

public class Mapa {
    private double x, y;
    private TiledMap map;
    private ArrayList<Hitbox> blocks;   
    private Hitbox Character;
    public Mapa(String ruta, GameContainer gc) {
        try {
            this.map = new TiledMap(ruta);
            //Carga de elementos del mapa
            blocks = new ArrayList<>();
            Character=new Hitbox(gc.getWidth()/2, gc.getHeight()/2, 30, 30);
            cargaMuros();        /*TODO: cargaSaltosEstado */
        } catch (SlickException ex) {
            Logger.getLogger(Mapa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public TiledMap getMap() {
        return map;
    }
    public void setMap(TiledMap map) {
        this.map = map;
    }
    public ArrayList<Hitbox> getBlocks() {
        return blocks;
    }
    public void setBlocks(ArrayList<Hitbox> blocks) {
        this.blocks = blocks;
    }
    private void cargaMuros() {
        int collisions = map.getLayerIndex("Walls"); //TODO: definir otro nombre para la capa
        if (collisions != -1) {    //Si encuentra la capa
            for (int j = 0; j < map.getHeight(); j++) {
                for (int i = 0; i < map.getWidth(); i++) {
                    if (map.getTileId(i, j, collisions) != 0) {
                        blocks.add(new Hitbox((float) i * 32, (float) j * 32, 32, 32));  //32 = ancho del patron
                    }
                }
            }
        }
    }
    public void actualizarPersonaje(float x, float y) {
        Character.updatePos(x, y);
    }
    public void Movimiento(int i, GameContainer gc)
    {
        if (gc.getInput().isKeyDown(Input.KEY_W) || gc.getInput().isKeyDown(Input.KEY_UP)) {
            y += i*.1f;  //i=tiempo de update
            actualizarMuros(0, +(i*.1f));
            collisions(i, gc, "ARRIBA");
        }
        if (gc.getInput().isKeyDown(Input.KEY_S) || gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            y -= i*.1f;  //i=tiempo de update
            actualizarMuros(0, -(i*.1f));
            collisions(i, gc, "ABAJO");
        }
        if (gc.getInput().isKeyDown(Input.KEY_A) || gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            x += i*.1f;  //i=tiempo de update
            actualizarMuros(+(i*.1f), 0);
            collisions(i, gc, "IZQ");
        }
        if (gc.getInput().isKeyDown(Input.KEY_D) || gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
            x -= i*.1f;  //i=tiempo de update
            actualizarMuros(-(i*.1f), 0);
            collisions(i, gc, "DCHA");
        }
    }
    public void actualizarMuros(float x, float y)
    {
        for(int i=0;i<blocks.size();i++)
        {
            blocks.get(i).updatePos(x, y);
        }
    }
    public void renderMap(GameContainer gc, Graphics grphcs, boolean ver_hitbox) {
        map.render((int) this.x, (int) this.y, 0, 0, gc.getWidth(), gc.getHeight());
        boolean amarillo=true;
        for (int i=0;i<blocks.size();i++) {
                grphcs.setColor(Color.black);
                grphcs.drawRect(blocks.get(i).getRectangulo().getX(), blocks.get(i).getRectangulo().getY(), blocks.get(i).getRectangulo().getWidth(), blocks.get(i).getRectangulo().getHeight());
            }
            grphcs.drawRect(Character.getRectangulo().getX(), Character.getRectangulo().getY(), Character.getRectangulo().getHeight(), Character.getRectangulo().getWidth());
        }
    public void collisions(int i, GameContainer gc, String dir)
    {
        for(int j=0;j<blocks.size();j++)
        {
            if(blocks.get(j).getRectangulo().intersects(Character.getRectangulo()))
            {
                if(dir.toUpperCase().equals("ABAJO"))
                {
                    y += i*.1f;  //i=tiempo de update
                    actualizarMuros(0, (i*.1f));
                }
                else if(dir.toUpperCase().equals("ARRIBA"))
                {
                    y -= i*.1f;  //i=tiempo de update
                    actualizarMuros(0, -(i*.1f));
                }
                else if(dir.toUpperCase().equals("DCHA"))
                {
                    x += i*.1f;  //i=tiempo de update
                    actualizarMuros((i*.1f), 0);
                     
                }
                else if(dir.toUpperCase().equals("IZQ"))
                {
                    x -= i*.1f;  //i=tiempo de update
                    actualizarMuros(-(i*.1f), 0);
                }
            }
        }
    }

}
   

