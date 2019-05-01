/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import Entities.Characters.Enemy;
import Entities.Characters.NPC;
import Entities.Characters.PlayableCharacter;
import Map.Mapa;
import static States.S0_MainMenu.lastStage;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author jgome
 */
public class StateRoom extends BasicGameState{
    private Mapa map;
    private boolean fog=true;
    public static final int room = 20;
    public static final int laberinth=21;
    public static final int puzzle=22;
    public static final int transport=23;
    public static final int bossfight=24;
    private boolean interact=false;
    private PlayableCharacter Char;
    private ArrayList<NPC> npcs;
    private Enemy enemy;
    
    public StateRoom(int state)
    {
        
    }
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Char=new PlayableCharacter("id",(float) gc.getWidth()/2,(float) gc.getHeight()/2, "pCName",  50, 100);
        map=new Mapa("src/Tiled/Habitacion.tmx", gc, Char, npcs, enemy);
        int positionx=200, positiony=200;
        map.setX(positionx);
        map.setY(positiony);
        map.actualizarIt(positionx,positiony);
        map.actualizarMuros(positionx,positiony);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        map.renderMap(gc, g, true);
        g.setColor(Color.white);
        interact(g, sbg, gc);
        g.setColor(Color.white);
        g.drawString("the position of the char= x: "+map.getX()+"y: "+map.getY(), 40, 40);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        map.Movimiento((int) map.getCharacter().getSpeed(), gc);
        interact=map.interact();
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
           sbg.enterState(5);
           lastStage = sbg.getCurrentStateID();
        }
    }
    public int getID() {
        return 20;
    }
    public void interact(Graphics g, StateBasedGame sbg, GameContainer gc)
    {
        Input input = gc.getInput();
        if(interact)
        {
            g.drawString("INTERACT", (int) map.getCharacter().getXPos()-20, (int) map.getCharacter().getYPos()+32);
            if(input.isKeyDown(Input.KEY_ENTER))
            {
                if(map.getX()<=367 && map.getX()>=333 && map.getY()<=45)
                {
                     sbg.enterState(laberinth);
                }
                if(map.getX()<=270 && map.getX()>=204 && map.getY()<=45)
                {
                     sbg.enterState(transport);
                }
                if(map.getX()<=142 && map.getX()>=108 && map.getY()<=45)
                {
                     sbg.enterState(puzzle);
                }
                if(map.getY()>=200)
                {
                     sbg.enterState(bossfight);
                }
            }
            interact=false;
        }
    }

}

