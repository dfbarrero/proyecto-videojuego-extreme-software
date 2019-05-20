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
import TextDisplay.TextDisplay;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class End extends BasicGameState{
private TextDisplay td = null;
    private String texto;
    private boolean showText = false, read=true;
    private Input input = null;
    private Timer timer = null;
    private Image NPC;
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
    private File archivo;
    private FileReader fr;
    private BufferedReader br;
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
        NPC=new Image("src/NPC/Idle.png");
        td=new TextDisplay(gc);
        timer = new Timer(true);
        archivo = new File("src/Archivo/Final.txt");
        try {
            fr = new FileReader (archivo);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StateRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
        br = new BufferedReader(fr);
    }

    @Override
    //Draws things on the screen
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
            g.setLineWidth(4);
            playersOptionsTTF.drawString(225,200,"Congratulations", notChosen);
            playersOptionsTTF.drawString(150,240,"you have finished the game", notChosen);
            playersOptionsTTF.drawString(150,350,"press enter to restart", notChosen);
             if(read)
        {
            g.setColor(Color.black);
            g.fillRect(0, 475, 800, 600);
            g.drawImage(NPC, 5, 500);
            if(isShowText())
            {
                td.displayText();
            }
        }
    }

    @Override
    //Make possible the movement
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        //Consigue la posicion del raton
        int xpos = Mouse.getX();
        int ypos = Mouse.getY();
        Input in=gc.getInput();
        if(read)
        {
            
            if(in.isKeyPressed(Input.KEY_ENTER))
            {
                try {
                    texto=br.readLine();
                } catch (IOException ex) {
                    Logger.getLogger(End.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(texto==null) read=false;
                else  td.setText(texto, 100, 550);
                    isTextShowing(true);
                    timer.schedule(new TimerTask()
                    {
                        public void run()
                            {
                                isTextShowing(false);
                            }
                    }, 3000);
            }
        }
        else
        {
        if(in.isKeyPressed(Input.KEY_ENTER))
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
    public Input getInput()
    {
        return input;
    }
    
    public void isTextShowing(boolean newValue)
    {
        showText = newValue;
    }
    
    public boolean isShowText()
    {
        return showText;
    }
}

