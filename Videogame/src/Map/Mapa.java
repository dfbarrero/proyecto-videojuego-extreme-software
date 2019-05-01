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

import Map.Hitbox;
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
import Entities.Characters.*;
public class Mapa {
    private double x, y;
    private TiledMap map;
    private ArrayList<Hitbox> blocks;   
    private PlayableCharacter Character;
    private ArrayList<NPC> npcs;
    private Enemy enemy;
    private ArrayList<Hitbox> iteracciones;
    public Mapa(String ruta, GameContainer gc, PlayableCharacter Char, ArrayList<NPC> npcs, Enemy enemy) {
        try {
            this.map = new TiledMap(ruta);
            //Carga de elementos del mapa

            this.npcs=npcs;
            this.enemy=enemy;
            blocks = new ArrayList<>();
            iteracciones=new ArrayList<>();
            this.Character=Char;
            cargaMuros();        /*TODO: cargaSaltosEstado */
            cargarIteracciones();
        } catch (SlickException ex) {
            Logger.getLogger(Mapa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public PlayableCharacter getCharacter() {
        return Character;
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
                    if (map.getTileId(i, j, collisions)!=0) {
                        blocks.add(new Hitbox((float) i * 32, (float) j * 32, 32, 32));  //32 = ancho del patron
                    }
                }
            }
        }
    }
    private void cargarIteracciones() {
        int collisions = map.getLayerIndex("Iteracciones"); //TODO: definir otro nombre para la capa
        if (collisions != -1) {    //Si encuentra la capa
            for (int j = 0; j < map.getHeight(); j++) {
                for (int i = 0; i < map.getWidth(); i++) {
                    if (map.getTileId(i, j, collisions) !=0) {
                        iteracciones.add(new Hitbox((float) ((float) i * 32-5.5), (float) ((float) j * 32-4), 42, 42));  //32 = ancho del patron
                    }
                }
            }
        }
    }
    public void actualizarPersonaje(float x, float y) {
        Character.getCollisionBox().updatePos(x, y);
    }
    public void Movimiento(int i, GameContainer gc)
    {
        if (gc.getInput().isKeyDown(Input.KEY_W) || gc.getInput().isKeyDown(Input.KEY_UP)) {
            y += i*.1f;  //i=tiempo de update
            actualizarMuros(0, +(i*.1f));
            actualizarIt(0, +(i*.1f));
            collisions(i, gc, "ARRIBA");
        }
        if (gc.getInput().isKeyDown(Input.KEY_S) || gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            y -= i*.1f;  //i=tiempo de update
            actualizarMuros(0, -(i*.1f));
            actualizarIt(0, -(i*.1f));
            collisions(i, gc, "ABAJO");
        }
        if (gc.getInput().isKeyDown(Input.KEY_A) || gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            x += i*.1f;  //i=tiempo de update
            actualizarMuros(+(i*.1f), 0);
            actualizarIt(+(i*.1f),0);
            collisions(i, gc, "IZQ");
        }
        if (gc.getInput().isKeyDown(Input.KEY_D) || gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
            x -= i*.1f;  //i=tiempo de update
            actualizarMuros(-(i*.1f), 0);
            actualizarIt(-(i*.1f),0);
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
    public void actualizarIt(float x, float y)
    {
        for(int i=0;i<iteracciones.size();i++)
        {
            iteracciones.get(i).updatePos(x, y);
        }
    }
    public void renderMap(GameContainer gc, Graphics grphcs, boolean ver_hitbox) {
        map.render((int) this.x, (int) this.y, 0, 0, gc.getWidth(), gc.getHeight());
        /*for (int i=0;i<blocks.size();i++) {
                grphcs.setColor(Color.black);
                grphcs.drawRect(blocks.get(i).getRectangulo().getX(), blocks.get(i).getRectangulo().getY(), blocks.get(i).getRectangulo().getWidth(), blocks.get(i).getRectangulo().getHeight());
            }*/
        for (int i=0;i<iteracciones.size();i++) {
                grphcs.setColor(Color.magenta);
                grphcs.drawRect(iteracciones.get(i).getRectangulo().getX(), iteracciones.get(i).getRectangulo().getY(), iteracciones.get(i).getRectangulo().getWidth(), iteracciones.get(i).getRectangulo().getHeight());
            }
            grphcs.setColor(Color.black);
            grphcs.drawRect(Character.getCollisionBox().getRectangulo().getX(), Character.getCollisionBox().getRectangulo().getY(), Character.getCollisionBox().getRectangulo().getHeight(), Character.getCollisionBox().getRectangulo().getWidth());
        }
    public void collisions(int i, GameContainer gc, String dir)
    {
        for(int j=0;j<blocks.size();j++)
        {
            if(blocks.get(j).getRectangulo().intersects(Character.getCollisionBox().getRectangulo()))
            {
                if(dir.toUpperCase().equals("ABAJO"))
                {
                    y += i*.1f;  //i=tiempo de update
                    actualizarMuros(0, (i*.1f));
                    actualizarIt(0,(i*.1f));
                }
                else if(dir.toUpperCase().equals("ARRIBA"))
                {
                    y -= i*.1f;  //i=tiempo de update
                    actualizarMuros(0, -(i*.1f));
                    actualizarIt(0,-(i*.1f));
                }
                else if(dir.toUpperCase().equals("DCHA"))
                {
                    x += i*.1f;  //i=tiempo de update
                    actualizarMuros((i*.1f), 0);
                    actualizarIt((i*.1f),0);
                     
                }
                else if(dir.toUpperCase().equals("IZQ"))
                {
                    x -= i*.1f;  //i=tiempo de update
                    actualizarMuros(-(i*.1f), 0);
                    actualizarIt(-(i*.1f),0);
                }
            }
        }
    }
    public boolean interact()
    {
        for(int j=0;j<iteracciones.size();j++)
        {
            if(iteracciones.get(j).getRectangulo().intersects(Character.getCollisionBox().getRectangulo()))
            {
                return true;
            }
    }
        return false;
    }
}
   

