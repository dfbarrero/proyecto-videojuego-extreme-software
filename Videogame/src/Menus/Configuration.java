/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 *
 * @author razvanvc
 */
public class Configuration extends BasicGameState {

    
    
    public Configuration(int configuration) {
    }

    @Override
    //Initialice some stuff (dont know yet)
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    @Override
    //Draws things on the screen
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    }

    @Override
    //Make possible the movement
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        
    }
    
    @Override
    //Return the state of the menu (0)
    public int getID() {
        return 2;
    }
}
