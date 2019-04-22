/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import static States.S0_MainMenu.lastStage;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import Map.*;
/**
 *
 * @author razvanvc
 */


public class S1_Playing extends BasicGameState {
    private Mapa map;
    
    public S1_Playing(int playing) {
    }

    @Override
    //Initialice some stuff (dont know yet)
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        map=new Mapa("Tiled/Mapa.tmx", gc);
    }

    @Override
    //Draws things on the screen
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawString("You're in the Play page",100,100);
        map.renderMap(gc, g, true);
        g.setColor(Color.white);
        interact(g);
    }

    @Override
    //Make possible the movement
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        map.Movimiento(delta, gc);
        interact=map.interact();
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
           sbg.enterState(5);
           lastStage = sbg.getCurrentStateID();
        }
    }
    @Override
    //Return the state of the menu (0)
    public int getID() {
        return 1;
    }
    public void interact(Graphics g)
    {
        if(interact)
        {
            g.drawString("INTERACT", (int) map.getCharacter().getPosx()-20, (int) map.getCharacter().getPosy()+32);
            interact=false;
        }
    }

}
