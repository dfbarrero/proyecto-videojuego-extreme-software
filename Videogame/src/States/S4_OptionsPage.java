/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import static States.S0_MainMenu.lastStage;
import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author razvanvc
 */
public class S4_OptionsPage extends BasicGameState{

    private int playersChoice = 0;
    private static final int NOCHOICES = 4;
    private static final int GRAFICS = 0;
    private static final int SOUND = 1;
    private static final int LANGUAGE = 2;
    private static final int BACK = 3;
    private TrueTypeFont playersOptionsTTF;
    private String[] playersOptions=new String[NOCHOICES];
    private final Color notChosen = new Color(153, 204, 255);
    private Font font;

    public S4_OptionsPage(int state) {
    }

    
    

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        playersOptions[0] = "Grafics";
        playersOptions[1] = "Sound";
        playersOptions[2] = "Language";
        playersOptions[3] = "Back";
        font = new Font("Verdana", Font.BOLD, 40);
        playersOptionsTTF = new TrueTypeFont(font, true);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawString("Your in option page",100,100);
        renderPlayersOptions();
        
    }

    @Override
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
                case GRAFICS:
                    sbg.enterState(7);
                    break;
                case SOUND:
                    sbg.enterState(8);
                    break;
                case LANGUAGE:
                    sbg.enterState(9);
                    break;
                case BACK:
                    sbg.enterState(lastStage);
                    break;
            }   
        }
    }
    @Override
    public int getID() {
        return 4;
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
