/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import States.*;
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
    public static final int escpage = 5;
    public static final int sureexit = 6;
    
    
    public Game(String name) {
        super(gamename);
        this.addState((GameState) new S0_MainMenu(menu));
        this.addState((GameState) new S1_Playing(playing));
        this.addState((GameState) new S3_LoadPage(loadpage));
        this.addState((GameState) new S2_SavePage(savepage));
        this.addState((GameState) new S4_OptionsPage(optionpage));
        this.addState((GameState) new S5_EscPage(escpage));
        this.addState((GameState) new S6_ExitSure(sureexit));
    }
    
    /**
     * Inicializar la lista de estado
     * @param gc
     * @throws SlickException 
     */
    
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(menu).init(gc, this);
        this.getState(playing).init(gc, this);
        this.getState(loadpage).init(gc, this);
        this.getState(savepage).init(gc, this);
        this.getState(optionpage).init(gc, this);
        this.getState(escpage).init(gc, this);
        this.getState(sureexit).init(gc, this);
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
