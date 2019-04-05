/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

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
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Mapa {

    private TiledMap map;
    private ArrayList<Hitbox> blocks;   
    private Hitbox Character;
    public Mapa(String ruta) {
        try {
            this.map = new TiledMap(ruta);
            //Carga de elementos del mapa
            blocks = new ArrayList<>();
            Character=new Hitbox(32, 32, 32, 32);
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
        boolean collision;
        Character.updatePos(x, y);
    }
    public void actualizarBloques(float x, float y)
    {
        for(int i=0;i<blocks.size();i++)
        {
            blocks.get(i).updatePos(x, y);
        }
    }
    public void renderMap(GameContainer gc, double x, double y, Graphics grphcs, boolean ver_hitbox) {
        map.render((int) x, (int) y, 0, 0, gc.getWidth(), gc.getHeight());
        for (int i=0;i<blocks.size();i++) {
                grphcs.setColor(Color.black);
                grphcs.drawRect(blocks.get(i).getRectangulo().getX(), blocks.get(i).getRectangulo().getY(), blocks.get(i).getRectangulo().getWidth(), blocks.get(i).getRectangulo().getHeight());
            }
            grphcs.drawRect(Character.getRectangulo().getX(), Character.getRectangulo().getY(), Character.getRectangulo().getHeight(), Character.getRectangulo().getWidth());
        }
    }
   

