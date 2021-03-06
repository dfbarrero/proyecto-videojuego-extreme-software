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
import org.newdawn.slick.Animation;
public class BossMap {
    private double x, y;
    private float bossx, bossy;
    private MainCharAnimation animations;
    private TiledMap map;
    private ArrayList<Hitbox> blocks;   
    private PlayableCharacter Character;
    private ArrayList<NPC> npcs;
    private Enemy enemy;
    private EnemyCharAnimation bossAnim;
    private float speed;
    private Animation animation;
    private ArrayList<Hitbox> iteracciones;
    public BossMap(String ruta, GameContainer gc) {
        try {
            this.map = new TiledMap(ruta);
            //Carga de elementos del mapa
            blocks = new ArrayList<>();
            bossx=388; bossy=-165;
            iteracciones=new ArrayList<>();
            cargaMuros();        /*TODO: cargaSaltosEstado */
            cargarIteracciones();
            animations=new MainCharAnimation();
            bossAnim=new EnemyCharAnimation();
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

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setCharacter(PlayableCharacter Character) {
        this.Character = Character;
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
        Character.getCollision().updatePos(x, y);
    }
    public void Movimiento(int i, GameContainer gc)
    {
        if (gc.getInput().isKeyDown(Input.KEY_W) || gc.getInput().isKeyDown(Input.KEY_UP)) {
            y += i*speed;  //i=tiempo de update
            bossy+=i*speed;
            actualizarMuros(0, +(i*speed));
            actualizarIt(0, +(i*speed));
            collisions(i, gc, "ARRIBA");
            animation=animations.getMainCharUpAnim();
            if (gc.getInput().isKeyDown(Input.KEY_D) || gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
            x -= i*speed;  //i=tiempo de update
            bossx-=i*speed;
            actualizarMuros(-(i*speed), 0);
            actualizarIt(-(i*speed),0);
            collisions(i, gc, "DCHA");
            animation=animations.getMainCharRightAnim();
            }else if (gc.getInput().isKeyDown(Input.KEY_A) || gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            x += i*speed;  //i=tiempo de update
            bossx+=i*speed;
            actualizarMuros(+(i*speed), 0);
            actualizarIt(+(i*speed),0);
            collisions(i, gc, "IZQ");
            animation=animations.getMainCharLeftAnim();  
            }
        }
        else if (gc.getInput().isKeyDown(Input.KEY_S) || gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            y -= i*speed;  //i=tiempo de update
            bossy-=i*speed;
            actualizarMuros(0, -(i*speed));
            actualizarIt(0, -(i*speed));
            collisions(i, gc, "ABAJO");
            animation=animations.getMainCharRightAnim();
            if (gc.getInput().isKeyDown(Input.KEY_D) || gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
            x -= i*speed;  //i=tiempo de update
            bossx-=i*speed;
            actualizarMuros(-(i*speed), 0);
            actualizarIt(-(i*speed),0);
            collisions(i, gc, "DCHA");
            animation=animations.getMainCharRightAnim();
            }else if (gc.getInput().isKeyDown(Input.KEY_A) || gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            x += i*speed;  //i=tiempo de update
            bossx+=i*speed;
            actualizarMuros(+(i*speed), 0);
            actualizarIt(+(i*speed),0);
            collisions(i, gc, "IZQ");
            animation=animations.getMainCharLeftAnim();  
            }
        }
        else if (gc.getInput().isKeyDown(Input.KEY_A) || gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            x += i*speed;  //i=tiempo de update
            bossx+=i*speed;
            actualizarMuros(+(i*speed), 0);
            actualizarIt(+(i*speed),0);
            collisions(i, gc, "IZQ");
            animation=animations.getMainCharLeftAnim();
            if (gc.getInput().isKeyDown(Input.KEY_W) || gc.getInput().isKeyDown(Input.KEY_UP)) {
            y += i*speed;  //i=tiempo de update
            bossy+=i*speed;
            actualizarMuros(0, +(i*speed));
            actualizarIt(0, +(i*speed));
            collisions(i, gc, "ARRIBA");
            animation=animations.getMainCharUpAnim();
            }
            else if (gc.getInput().isKeyDown(Input.KEY_S) || gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            y -= i*speed;  //i=tiempo de update
            bossy-=i*speed;
            actualizarMuros(0, -(i*speed));
            actualizarIt(0, -(i*speed));
            collisions(i, gc, "ABAJO");
            animation=animations.getMainCharRightAnim();
            }
        
        }
        else if (gc.getInput().isKeyDown(Input.KEY_D) || gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
            x -= i*speed;  //i=tiempo de update
            bossx-=i*speed;
            actualizarMuros(-(i*speed), 0);
            actualizarIt(-(i*speed),0);
            collisions(i, gc, "DCHA");
            animation=animations.getMainCharRightAnim();
            if (gc.getInput().isKeyDown(Input.KEY_W) || gc.getInput().isKeyDown(Input.KEY_UP)) {
            y += i*speed;  //i=tiempo de update
            bossy+=i*speed;
            actualizarMuros(0, +(i*speed));
            actualizarIt(0, +(i*speed));
            collisions(i, gc, "ARRIBA");
            animation=animations.getMainCharUpAnim();
        }else if (gc.getInput().isKeyDown(Input.KEY_S) || gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            y -= i*speed;  //i=tiempo de update
            bossy-=i*speed;
            actualizarMuros(0, -(i*speed));
            actualizarIt(0, -(i*speed));
            collisions(i, gc, "ABAJO");
            animation=animations.getMainCharRightAnim();
        }
        }
        else animation=animations.getMainCharIdleAnim();
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
        /**for (int i=0;i<blocks.size();i++) {
                grphcs.setColor(Color.black);
                grphcs.drawRect(blocks.get(i).getRectangulo().getX(), blocks.get(i).getRectangulo().getY(), blocks.get(i).getRectangulo().getWidth(), blocks.get(i).getRectangulo().getHeight());
            }
        for (int i=0;i<iteracciones.size();i++) {
                grphcs.setColor(Color.magenta);
                grphcs.drawRect(iteracciones.get(i).getRectangulo().getX(), iteracciones.get(i).getRectangulo().getY(), iteracciones.get(i).getRectangulo().getWidth(), iteracciones.get(i).getRectangulo().getHeight());
            }
            grphcs.setColor(Color.black);
            grphcs.drawRect(Character.getCollision().getRectangulo().getX(), Character.getCollision().getRectangulo().getY(), Character.getCollision().getRectangulo().getHeight(), Character.getCollision().getRectangulo().getWidth());
        */
        }
    public void renderBoss(GameContainer gc, Graphics grphcs, boolean ver_hitbox)
    {
        grphcs.drawAnimation(bossAnim.getEnemyCharIdleSmallAnim(), bossx, bossy);
    }
    public void collisions(int i, GameContainer gc, String dir)
    {
        for(int j=0;j<blocks.size();j++)
        {
            if(blocks.get(j).getRectangulo().intersects(Character.getCollision().getRectangulo()))
            {
                if(dir.toUpperCase().equals("ABAJO"))
                {
                    y += i*speed;  //i=tiempo de update
                    bossy+=i*speed;
                    actualizarMuros(0, (i*speed));
                    actualizarIt(0,(i*speed));
                }
                else if(dir.toUpperCase().equals("ARRIBA"))
                {
                    y -= i*speed;  //i=tiempo de update
                    bossy-=i*speed;
                    actualizarMuros(0, -(i*speed));
                    actualizarIt(0,-(i*speed));
                }
                else if(dir.toUpperCase().equals("DCHA"))
                {
                    
                    x += i*speed;  //i=tiempo de update
                    bossx+=i*speed;
                    actualizarMuros((i*speed), 0);
                    actualizarIt((i*speed),0);
                     
                }
                else if(dir.toUpperCase().equals("IZQ"))
                {
                    
                    x -= i*speed;  //i=tiempo de update
                    bossx-=i*speed;
                    actualizarMuros(-(i*speed), 0);
                    actualizarIt(-(i*speed),0);
                }
            }
        }
    }
    public boolean interact()
    {
        for(int j=0;j<iteracciones.size();j++)
        {
            if(iteracciones.get(j).getRectangulo().intersects(Character.getCollision().getRectangulo()))
            {
                return true;
            }
    }
        return false;
    }

    public ArrayList<NPC> getNpcs() {
        return npcs;
    }

    public void setNpcs(ArrayList<NPC> npcs) {
        this.npcs = npcs;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public ArrayList<Hitbox> getIteracciones() {
        return iteracciones;
    }

    public void setIteracciones(ArrayList<Hitbox> iteracciones) {
        this.iteracciones = iteracciones;
    }
    
}
   

