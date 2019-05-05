/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import Entities.Characters.Enemy;
import Entities.Characters.NPC;
import Entities.Characters.PlayableCharacter;
import Entities.Items.*;
import Map.Mapa;
import static States.S0_MainMenu.lastStage;
import static States.StateRoom.bossfight;
import static States.StateRoom.laberinth;
import static States.StateRoom.puzzle;
import static States.StateRoom.transport;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import static org.newdawn.slick.Color.black;
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
public class StateLaberinth extends BasicGameState{
    private Mapa map;
    private boolean fog=true;
    private boolean interact=false;
    private PlayableCharacter Char;
    private ArrayList<NPC> npcs;
    private Enemy enemy;
    private Sword espada;
    private Bow arco;
    boolean sword=false, llaveb=false, bow=false, flechas=false;
    private int contfl=0;
    private Key llave;
    public StateLaberinth(int state)
    {
        
    }

    @Override
    public int getID() {
        return 21;
    }
public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Char=new PlayableCharacter(new Image("src/Sprites/Idle (1).png"),"id",(float) gc.getWidth()/2,(float) gc.getHeight()/2, "pCName", 30, 100);
        map=new Mapa("src/Tiled/Laberinth.tmx", gc, Char, npcs, enemy);
        int positionx=-625, positiony=-405;
        map.setX(positionx);
        map.setY(positiony);
        map.actualizarIt(positionx,positiony);
        map.actualizarMuros(positionx,positiony);
        llave=new Key("LLave lab", "1");
        arco=new Bow("409506", 100, 0, "matareyes");
        espada=new Sword("333333", 50, 200, "Sombra");
    }

    @Override
    //Draws things on the screen
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        
        if(fog)
        {
            map.renderMap(gc, g, true);
            g.setColor(Color.white);
            interact(g, gc, sbg);
            g.setColor(black);
            g.fillRect(0, 0, gc.getWidth(), (float) (gc.getHeight()/(2.55)));
            g.fillRect(0, 0, (float) (gc.getWidth()/(2.4)), gc.getHeight());
            g.fillRect(0, (float) (gc.getHeight()/(1.54)), gc.getWidth(), (float) (gc.getHeight()/2.4));
            g.fillRect((float) (gc.getWidth()/(1.62)),0, gc.getWidth()/(2), gc.getHeight());
            
        }
        else
        {
            map.renderMap(gc, g, true);
            g.setColor(Color.white);
            interact(g, gc, sbg);
        }
        g.setColor(Color.black);
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
    }
    //Return the state of the menu (0)
    public void interact(Graphics g, GameContainer gc, StateBasedGame sbg)
    {
        Input input=gc.getInput();
        if(interact && (!sword || !bow || !flechas || !llaveb))
        {
            g.drawString("INTERACT", (int) map.getCharacter().getXPos()-20, (int) map.getCharacter().getYPos()+32);
            if(input.isKeyPressed(Input.KEY_ENTER))
            {
                if(map.getX()<=-205 && map.getX()>=-245 && map.getY()>=-475 && map.getY()<=-405)
                {
                     fog=false;
                }
                else if(map.getX()>=-60 && map.getX()<=-25 && map.getY()>=-955 && map.getY()<=-885 && !sword)
                {
                     espada.recoger(Char);
                     System.out.println("Espada recogida");
                     sword=true;
                }
                else if(map.getY()>=35 && map.getX()<=-1270 && map.getX()>=-1300 && !bow)
                {
                     arco.recoger(Char);
                     System.out.println("Arco recogida");
                     bow=true;
                }
                else if(map.getY()<=-1380)
                {
                     llave.recoger(Char);
                     System.out.println("Llave recogida");
                     llaveb=true;
                }
                else if(!flechas)
                {
                    arco.addarrows(10);
                    System.out.println("Arrows areron");
                    contfl++;
                    if(contfl>=2)
                    {
                        flechas=true;
                    }
                }
            }
        }
    }

}
