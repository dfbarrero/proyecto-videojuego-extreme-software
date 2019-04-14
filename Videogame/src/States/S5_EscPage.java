/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import static States.S0_MainMenu.lastStage;
/**
 *
 * @author razvanvc
 */
public class S5_EscPage extends BasicGameState{
    
    private int playersChoice = 0;
    private static final int NOCHOICES = 4;
    private static final int BACK = 0;
    private static final int SAVE = 1;
    private static final int OPTIONS = 2;
    private static final int QUIT = 3;
    private final String[] playersOptions = new String[NOCHOICES];
    private java.awt.Font font;
    private TrueTypeFont playersOptionsTTF;
    private final Color notChosen = new Color(153, 204, 255);
    
    
    public S5_EscPage(int playing) {
    }

    @Override
    //Initialice some stuff (dont know yet)
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        playersOptions[0] = "Back";
        playersOptions[1] = "Save";
        playersOptions[2] = "Options";
        playersOptions[3] = "Quit";
        lastStage = sbg.getCurrentStateID();
        
        font = new Font("Verdana", Font.BOLD, 25);
        playersOptionsTTF = new TrueTypeFont(font, true);
    }

    @Override
    //Draws things on the screen
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawString("You're in the Escape page",100,100);
        g.drawString(Integer.toString(sbg.getCurrentStateID()), 100, 50);
        renderPlayersOptions();
    }

    @Override
    //Make possible the movement
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
           sbg.enterState(lastStage);
        }
        
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
                case QUIT:
                    sbg.enterState(6);
                    break;
                case BACK:
                    sbg.enterState(1);
                    break;
                case SAVE:
                    sbg.enterState(2);
                    break;
                case OPTIONS:
                    sbg.enterState(4);
                    break;    
            }
        }
        
    }
    @Override
    //Return the state of the menu (0)
    public int getID() {
        return 5;
    }
    private void renderPlayersOptions() {
        for (int i = 0; i < NOCHOICES; i++) {
            if (playersChoice == i) {
                playersOptionsTTF.drawString(100, i * 50 + 200, playersOptions[i]);
            } else {
                playersOptionsTTF.drawString(100, i * 50 + 200, playersOptions[i], notChosen);
            }
        }
    }
    
    
}
