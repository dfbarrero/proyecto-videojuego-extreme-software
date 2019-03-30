/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;


import java.awt.Font;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import Game.*;

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
    private static final int DEFAULT = 8;
    private static final int R_720P = 0;
    private static final int R_800P = 1;
    private static final int R_900P = 2;
    private static final int R_1080P = 3;
    private static final int R_1200P = 4;
    private static final int R_1440P = 5;
    private static final int R_1600P = 6;
    private static final int R_4K = 7;
    

    
    
    private final String[] playersOptions = new String[DEFAULT];
    
    private Font font;
    private TrueTypeFont playersOptionsTTF;
    private final Color notChosen = new Color(153, 204, 255);
    private final Color background = new Color(0, 0, 255);
    private String mouse;
    private String choice1;

    
    private final String[] playersResolutions = new String[DEFAULT];
    
    public S7_Graphics(int graphics) {
        
    }

    @Override
    //Initialice some stuff (dont know yet)
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        playersOptions[0] = "Resolution";
        playersOptions[1] = "Fullscreen";
        playersOptions[2] = "Back";
        //playersOptions[2] = "Options";
        playersResolutions[0] = "1280 x 720"; //720p
        playersResolutions[1] = "1280 x 800"; //800p
        playersResolutions[2] = "1600 x 900"; //900p
        playersResolutions[3] = "1920 x 1080"; //1080p
        playersResolutions[4] = "1920 x 1200"; //1200p
        playersResolutions[5] = "2560 x 1440"; //1440p
        playersResolutions[6] = "2560 x 1600"; //1600p
        playersResolutions[7] = "3840 x 2160"; //4K

        
        font = new Font("Verdana", Font.BOLD, 40);
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
                    gc.setFullscreen(true);
                    break;
                case R_800P:
                    break;
                case R_900P:
                    break;    
                case R_1080P:
                    break;
                case R_1200P:
                    break;
                case R_1440P:
                    break; 
                case R_1600P:
                    break;
                case R_4K:
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