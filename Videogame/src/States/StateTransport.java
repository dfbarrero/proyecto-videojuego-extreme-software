/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import Entities.Characters.Enemy;
import Entities.Characters.NPC;
import Entities.Characters.PlayableCharacter;
import Entities.Items.Key;
import Map.Mapa;
import static States.S0_MainMenu.lastStage;
import static States.StateRoom.bossfight;
import static States.StateRoom.laberinth;
import static States.StateRoom.puzzle;
import static States.StateRoom.transport;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author jgome
 */
public class StateTransport extends BasicGameState{
    private Mapa map;
    private boolean transport=false;
    private boolean interact=false;
    private PlayableCharacter Char;
    private ArrayList<NPC> npcs;
    private Enemy enemy;
    private Key llave;
    
    public StateTransport(int state)
    {
        
    }
    @Override
    //Initialice some stuff (dont know yet)
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Char=new PlayableCharacter(new Image("src/Sprites/Idle (1).png"),"id",(float) gc.getWidth()/2,(float) gc.getHeight()/2, "pCName",  30, 100);
        map=new Mapa("src/Tiled/Transport.tmx", gc, Char, npcs, enemy);
        int positionx=200, positiony=200;
        map.setX(positionx);
        map.setY(positiony);
        map.actualizarIt(positionx,positiony);
        map.actualizarMuros(positionx,positiony);
    }

    @Override
    //Draws things on the screen
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        map.renderMap(gc, g, true);
        g.setColor(Color.white);
        interact(g, sbg, gc);
        g.drawString("the position of the char= x: "+map.getX()+"y: "+map.getY(), 40, 40);
        map.getAnimation().draw(Char.getXPos(), Char.getYPos());
    }

    @Override
    //Make possible the movement
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        map.Movimiento((int) map.getCharacter().getSpeed(), gc);
        interact=map.interact();
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
           sbg.enterState(5);
           lastStage = sbg.getCurrentStateID();
        }
        transport(sbg, gc);
    }
    @Override
    //Return the state of the menu (0)
    public int getID() {
        return 23;
    }
    public void transport(StateBasedGame sbg, GameContainer gc)
    {
        double x, y;
        double tx, ty;
        Input input=gc.getInput();
        if(input.isKeyPressed(Input.KEY_ENTER) && interact)
            {
                //Bloque 1
                if(map.getX()<=362 && map.getX()>=299 && map.getY()<=-29 && map.getY()>=-91)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=335;
                    ty=-435;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=362 && map.getX()>=299 && map.getY()<=-186 && map.getY()>=-251)
                {
                     x=map.getX();
                    y=map.getY();
                    tx=325;
                    ty=-435;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=362 && map.getX()>=299 && map.getY()<=-411 && map.getY()>=-476)
                {
                     x=map.getX();
                    y=map.getY();
                    tx=330;
                    ty=-440;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=362 && map.getX()>=299 && map.getY()<=-571 && map.getY()>=-636)
                {
                     x=map.getX();
                    y=map.getY();
                    tx=330;
                    ty=-440;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                //Bloque 2
                else if(map.getX()<=233 && map.getX()>=172 && map.getY()<=196 && map.getY()>=135)
                {
                     x=map.getX();
                    y=map.getY();
                    tx=330;
                    ty=-440;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=233 && map.getX()>=172 && map.getY()<=6 && map.getY()>=-57)
                {
                     x=map.getX();
                    y=map.getY();
                    tx=330;
                    ty=-440;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=233 && map.getX()>=172 && map.getY()<=-220 && map.getY()>=-293)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=330;
                    ty=-440;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=233 && map.getX()>=172 && map.getY()<=-468 && map.getY()>=-547)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=330;
                    ty=-440;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                //Bloque 3
                else if(map.getX()<=108 && map.getX()>=40 && map.getY()<=6 && map.getY()>=-57)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=330;
                    ty=-440;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=108 && map.getX()>=40 && map.getY()<=-212 && map.getY()>=-293)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=330;
                    ty=-440;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=108 && map.getX()>=40 && map.getY()<=-441 && map.getY()>=-509)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=330;
                    ty=-440;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                //Bloque 4
                else if(map.getX()<=-14 && map.getX()>=-91 && map.getY()<=-569 && map.getY()>=-637)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=330;
                    ty=-440;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=-70 && map.getX()>=-150 && map.getY()<=-119 && map.getY()>=-192)
                {
                    sbg.enterState(20);
                }
                else if(map.getX()<=-148 && map.getX()>=-213 && map.getY()<=-569 && map.getY()>=-637)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=330;
                    ty=-440;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                // Bloque 5
                else if(map.getX()<=-404 && map.getX()>=-473 && map.getY()<=11 && map.getY()>=-60)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=330;
                    ty=-440;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=-404 && map.getX()>=-473 && map.getY()<=-192 && map.getY()>=-257)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=330;
                    ty=-440;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=-404 && map.getX()>=-473 && map.getY()<=-378 && map.getY()>=-443)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=330;
                    ty=-440;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=-404 && map.getX()>=-473 && map.getY()<=-503 && map.getY()>=-575)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=330;
                    ty=-440;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=-404 && map.getX()>=-473 && map.getY()<=-629 && map.getY()>=-707)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=330;
                    ty=-440;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=-404 && map.getX()>=-473 && map.getY()<=83 && map.getY()>=-159)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=330;
                    ty=-440;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                //Bloque 6
                else if(map.getX()<=-529 && map.getY()<=-83 && map.getY()>=-159)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=330;
                    ty=-440;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=-529 && map.getY()<=-368 && map.getY()>=-446)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=330;
                    ty=-440;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=-529 && map.getY()<=-501 && map.getY()>=-571)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=330;
                    ty=-440;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=-529 && map.getY()<=-634 && map.getY()>=-706)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=330;
                    ty=-440;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                //Bloque 7
                else if(map.getX()<=-278 && map.getX()>=-343 && map.getY()<=199 && map.getY()>=125)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=330;
                    ty=-440;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=-278 && map.getX()>=-343 && map.getY()<=-91 && map.getY()>=-163)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=330;
                    ty=-440;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=-278 && map.getX()>=-343 && map.getY()<=-405 && map.getY()>=-482)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=330;
                    ty=-440;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                } 
        }
    }
       public void interact(Graphics g, StateBasedGame sbg, GameContainer gc)
    {
        Input input = gc.getInput();
        if(interact)
        {
            g.drawString("INTERACT", (int) map.getCharacter().getXPos()-20, (int) map.getCharacter().getYPos()+32);
            interact=false;
        }
    }
    
}
