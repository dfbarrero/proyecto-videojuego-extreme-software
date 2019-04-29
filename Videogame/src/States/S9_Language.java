/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

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
public class S9_Language extends BasicGameState{

    public S9_Language(int language) {
        
    }
    @Override
    //Initialice some stuff (dont know yet)
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
    }

    @Override
    //Draws things on the screen
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawString("You're in the Language page",100,100);
    }

    @Override
    //Make possible the movement
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
           sbg.enterState(4);
        }
    }
    @Override
    //Return the state of the menu (0)
    public int getID() {
        return 9;
    }
}
