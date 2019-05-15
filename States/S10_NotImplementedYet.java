/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import static States.S0_MainMenu.lastStage;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author razvanvc
 */
public class S10_NotImplementedYet extends BasicGameState{

    
    public S10_NotImplementedYet(int state) {
    }
    
    @Override
    //Return the state of the menu (7)
    public int getID() {
        return 10;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawString("THIS STATE HAS NOT BEEN IMPLEMENTED YET. ",100,100);
        g.drawString("PRESS ESC TO GO TO THE LAST STAGE",100,250);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
           sbg.enterState(lastStage);
        }
    }
}
