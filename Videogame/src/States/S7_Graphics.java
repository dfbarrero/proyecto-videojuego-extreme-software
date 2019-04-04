/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;


import java.awt.Font;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author razvanvc
 */
public class S7_Graphics extends BasicGameState {

    private int playersChoice = 0;
    private static final int NOCHOICES = 3;
    private static final int RESOLUTION = 0;
    private static final int FULLSCREEN = 1;
    private static final int BACK = 2;
    // private static final int OPTIONS = 3;
    
    private int playersResolution = 0;
    private static final int DEFAULT = 9;
    private static final int R_720P = 1;
    private static final int R_800P = 2;
    private static final int R_900P = 3;
    private static final int R_1080P = 4;
    private static final int R_1200P = 5;
    private static final int R_1440P = 6;
    private static final int R_1600P = 7;
    private static final int R_4K = 8;
    private static final int R_DEFALT = 0;
    

    
    
    private final String[] playersOptions = new String[DEFAULT];
    
    private Font font;
    private TrueTypeFont playersOptionsTTF;
    private final Color notChosen = new Color(153, 204, 255);
    private final Color background = new Color(0, 0, 255);
    private String mouse;
    private String choice1;
    public boolean changesResolution;
    public boolean otherChanges;

    
    private final String[] playersResolutions = new String[DEFAULT];
    
    public S7_Graphics(int graphics) {
        
    }
    Properties prop=new Properties();
    FileInputStream ip;
    int lastResolutionChoosen;
        
        
        

    @Override
    //Initialice some stuff (dont know yet)
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        playersOptions[0] = "Resolution";
        playersOptions[1] = "Fullscreen";
        playersOptions[2] = "Back";
        //playersOptions[2] = "Options";
        playersResolutions[0] = "800 x 600";//xp
        playersResolutions[1] = "1280 x 720"; //720p
        playersResolutions[2] = "1280 x 800"; //800p
        playersResolutions[3] = "1600 x 900"; //900p
        playersResolutions[4] = "1920 x 1080"; //1080p
        playersResolutions[5] = "1920 x 1200"; //1200p
        playersResolutions[6] = "2560 x 1440"; //1440p
        playersResolutions[7] = "2560 x 1600"; //1600p
        playersResolutions[8] = "3840 x 2160"; //4K

        
        font = new Font("Verdana", Font.BOLD, 25);
        playersOptionsTTF = new TrueTypeFont(font, true);
        
    }

    @Override
    //Draws things on the screen
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawString("Your in Graphics Stage",100,100);
        
        //g.drawString(mouse, 950, 10);//muestra la posicion de raton
        renderPlayersOptions();
        renderResolutions(playersResolution);
        g.drawString(choice1, 100, 120);
        try{
            ip = new FileInputStream("src/Game/config.properties");
            prop.load(ip);
            
            lastResolutionChoosen = Integer.parseInt(prop.getProperty("lastResolutionChoosen"));
            
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }

    @Override
    //Make possible the movement
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
           sbg.enterState(4);
        }
        int xpos = Mouse.getX();
        int ypos = Mouse.getY();
        renderResolutions(playersResolution);
        choice1 = "Player Resolution: " + playersResolution + "Players Choice: " + playersChoice;
        //mouse = xpos + " " + ypos; //cambia la variable de la posicion del raton
        
        if (input.isKeyPressed(Input.KEY_DOWN)) {
            if (playersChoice == (NOCHOICES - 1)) {
                playersChoice = 0;
            } else {
                playersChoice++;
            }
        }
        if (input.isKeyPressed(Input.KEY_UP)) {
            if (playersChoice == 0) {
                playersChoice = NOCHOICES - 1;
            } else {
                playersChoice--;
            }
        }
        if (input.isKeyPressed(Input.KEY_ENTER)) {
            switch (playersChoice) {
                case BACK:
                    sbg.enterState(4);
                    break;
                case RESOLUTION:
                    break;
                case FULLSCREEN:
                    break;    
            }
        }
        
        if (playersChoice == RESOLUTION && input.isKeyPressed(Input.KEY_LEFT)){
            if (playersResolution == 0) {
                playersResolution = DEFAULT - 1;
                
            } else {
                playersResolution--;
                
            }
        }
        
        if (playersChoice == RESOLUTION && input.isKeyPressed(Input.KEY_RIGHT)){
            if (playersResolution == (DEFAULT - 1)) {
                playersResolution = 0;
                //playersResolution = lastResolutionChoosen;
                //renderResolutions(playersResolution);
            } else {
                playersResolution++;
                //renderResolutions(playersResolution);
                
            }
        }
        
        if (playersChoice == 0 && input.isKeyDown(Input.KEY_ENTER)) {//.isKeyPressed()
            gc.sleep(100);
            switch (playersResolution) {
                //PROPERTYES mirar configuracion
                case R_720P:
                    prop.setProperty("lastResolutionChoosen", "1");
            {
                try {
                    prop.store(new FileOutputStream("config.properties"), null);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(S7_Graphics.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(S7_Graphics.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
                case R_800P:
                    prop.setProperty("lastResolutionChoosen", "2");
                    break;
                case R_900P:
                    prop.setProperty("lastResolutionChoosen", "3");
                    break;    
                case R_1080P:
                    prop.setProperty("lastResolutionChoosen", "4");
                    break;
                case R_1200P:
                    prop.setProperty("lastResolutionChoosen", "5");
                    break;
                case R_1440P:
                    prop.setProperty("lastResolutionChoosen", "6");
                    break; 
                case R_1600P:
                    prop.setProperty("lastResolutionChoosen", "7");
                    break;
                case R_4K:
                    prop.setProperty("lastResolutionChoosen", "8");
                    break;
            }
        }
    }
    @Override
    //Return the state of the menu (0)
    public int getID() {
        return 7;
    }
    
    private void renderPlayersOptions() {
        for (int i = 0; i < NOCHOICES; i++) {
            if (playersChoice == i) {
                playersOptionsTTF.drawString(100, i * 50 + 200, playersOptions[i]);
                //playersOptionsTTF.drawString(400, i * 50 + 200, playersResolutions[8]);
            } else {
                playersOptionsTTF.drawString(100, i * 50 + 200, playersOptions[i], notChosen);
            }
        }
    }
    private void renderResolutions(int resolution){
        
        for (int i = 0; i < DEFAULT; i++) {
            
            if (playersChoice == 0) {
                playersOptionsTTF.drawString(400, 200, playersResolutions[resolution]);
                
            } else {
                playersOptionsTTF.drawString(400, 200, playersResolutions[resolution], notChosen);
            }
        }
    }
}