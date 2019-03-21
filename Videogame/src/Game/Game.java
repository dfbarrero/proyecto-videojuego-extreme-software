/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Menus.*;
import Playing.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 *
 * @author razvanvc
 */
public class Game extends StateBasedGame{
    
    public static final String gamename = "3xtremE";
    public static final int menu = 0;
    public static final int playing = 1;
    public static final int loadpage = 2;
    public static final int savepage = 3;
    public static final int optionpage = 4;
    
    
    public Game(String name) {
        super(gamename);
        this.addState((GameState) new PrincipalMenu(menu));
        this.addState((GameState) new Playing(playing));
        this.addState((GameState) new LoadPage(loadpage));
        this.addState((GameState) new SavePage(savepage));
        this.addState((GameState) new OptionsPage(optionpage));
    }
    
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(menu).init(gc, this);
        this.getState(playing).init(gc, this);
        this.getState(loadpage).init(gc, this);
        this.getState(savepage).init(gc, this);
        this.getState(optionpage).init(gc, this);
        this.enterState(menu);
    }
    
    
    public static void main(String[] args) {
        AppGameContainer appgc;
        try{
            appgc = new AppGameContainer (new Game(gamename));
            appgc.setDisplayMode(1080, 720, false);
            appgc.start();
        }catch(SlickException e){
            e.printStackTrace();
        }
    }

}
