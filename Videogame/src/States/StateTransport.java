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
    private boolean fog=true;
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
        Char=new PlayableCharacter("id",(float) gc.getWidth()/2,(float) gc.getHeight()/2, "pCName",  50, 100);
        map=new Mapa("src/Tiled/Transport.tmx", gc, Char, npcs, enemy);
        int positionx=200, positiony=200;
        map.setX(positionx);
        map.setY(positiony);
        map.actualizarIt(positionx,positiony);
        //map.actualizarMuros(positionx,positiony);
    }

    @Override
    //Draws things on the screen
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        map.renderMap(gc, g, true);
        g.setColor(Color.white);
        interact(g, sbg, gc);
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
    @Override
    //Return the state of the menu (0)
    public int getID() {
        return 23;
    }
       public void interact(Graphics g, StateBasedGame sbg, GameContainer gc)
    {
        Input input = gc.getInput();
        if(interact)
        {
            g.drawString("INTERACT", (int) map.getCharacter().getXPos()-20, (int) map.getCharacter().getYPos()+32);
            if(input.isKeyPressed(Input.KEY_ENTER))
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
