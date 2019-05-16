/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import Entities.Characters.PlayableCharacter;
import MusicPlayer.MusicPlayer;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author jgome
 */

import Entities.Characters.PlayableCharacter;
import MusicPlayer.*;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 *
 * @author razvanvc
 */
public class End extends BasicGameState{

    public String mouse = "No input yet!";
    Image play;
    private boolean start;
    private int playersChoice = 0;
    private static final int NOCHOICES = 4;
    private static final int START = 0;
    private static final int LOAD = 1;
    private static final int OPTIONS = 2;
    private static final int QUIT = 3;
    private final String[] playersOptions = new String[NOCHOICES];
    private Font font;
    private TrueTypeFont playersOptionsTTF;
    private final Color notChosen = new Color(153, 204, 255);
    private final Color background = new Color(0, 0, 255);
    public static int lastStage;
    private PlayableCharacter principal;
    private boolean playingMuscic = true;
    public MusicPlayer musicplayer = new MusicPlayer();
    private ObjectOutputStream save;
    
    public End(int state) {
    }
    
    
    @Override
    //Initialice some stuff (dont know yet)
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        start=true;
        try {
            //play = new Image("res/play button.png");
            principal=new PlayableCharacter("id",(float)gc.getWidth()/2,(float) gc.getHeight()/2, "pCName",  0.15f, 100);
            System.out.println("The speed of te character is: "+principal.getSpeed());
        } catch (IOException ex) {
            Logger.getLogger(S0_MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        playersOptions[0] = "Start";
        playersOptions[1] = "Load";
        playersOptions[2] = "Options";
        playersOptions[3] = "Quit";
        font = new Font("Italica", Font.ROMAN_BASELINE, 40);
        playersOptionsTTF = new TrueTypeFont(font, true);
        lastStage = sbg.getCurrentStateID();
        musicplayer.playTrack(1);
        try {
            this.save=new ObjectOutputStream(new FileOutputStream("src/Archivo/Character.dat"));
            //musicplayer.setVolume(); Implement function (dont work yet)
        } catch (FileNotFoundException ex) {
            Logger.getLogger(S0_MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(S0_MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    //Draws things on the screen
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawString(mouse, 950, 10);//muestra la posicion de raton
        if(start)
        {
            g.setLineWidth(4);
            playersOptionsTTF.drawString(225,200,"Congratulations", notChosen);
            playersOptionsTTF.drawString(150,240,"you ave finished the game", notChosen);
            playersOptionsTTF.drawString(150,450,"press enter to restart", notChosen);
        }
    }

    @Override
    //Make possible the movement
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        //Consigue la posicion del raton
        int xpos = Mouse.getX();
        int ypos = Mouse.getY();
        Input input=gc.getInput();
        mouse = xpos + " " + ypos; //cambia la variable de la posicion del raton
        if(input.isKeyPressed(Input.KEY_ENTER))
        {
            sbg.getState(20).init(gc, sbg);
            sbg.getState(21).init(gc, sbg);
            sbg.getState(22).init(gc, sbg);
            sbg.getState(23).init(gc, sbg);
            sbg.getState(24).init(gc, sbg);
            sbg.getState(25).init(gc, sbg);
            sbg.enterState(0);
        }
        }
    
    @Override
    //Return the state of the menu (0)
    public int getID() {
        return 50;
    }

    public void saveChar(PlayableCharacter Character) throws IOException
    {
        save.reset();
        save.writeObject(Character);
        save.close();
    }
}

